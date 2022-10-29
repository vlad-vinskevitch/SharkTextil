package com.shark.textil.security.service;

import com.shark.textil.domain.props.JwtProperties;
import com.shark.textil.domain.user.User;
import com.shark.textil.security.config.CryptoAES;
import com.shark.textil.security.exception.AuthenticationException;
import com.shark.textil.security.exception.JwtTokenException;
import com.shark.textil.security.jwt.JwtUser;
import com.shark.textil.security.mapper.JwtUserMapper;
import com.shark.textil.service.JwtTokenProviderService;
import com.shark.textil.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProviderService {

    private final JwtProperties jwtProperties;
    private final UserService userService;
    private final CryptoAES cryptoAES;
    private final JwtUserMapper jwtUserMapper;

    @Override
    public String createAccessToken(final User user) {
        final String email = this.cryptoAES.encrypt(user.getEmail(), this.jwtProperties.getSecret());
        final Claims claims = Jwts.claims()
                .setSubject(email);
        claims.put("roles", user.getAuthorities());
        log.info("Creating accessToken for: {}", user.getUserId());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getValidity().getTime()))
                .signWith(cryptoAES.getSigningKey(jwtProperties.getSecret()))
                .compact();
    }

    @Override
    public String createRefreshToken(final User user) {
        log.info("Creating refreshToken for: {}", user.getUserId());
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtProperties.getValidity().getRefreshTime()))
                .setSubject(this.cryptoAES.encrypt(user.getEmail(), this.jwtProperties.getSecret()))
                .signWith(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                .compact();
    }

    @Override
    public boolean validateToken(final String token) {
        log.info("Validating token: {}", token);
        try {
            final Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtTokenException();
        }
    }

    @Override
    public String getEmailFromToken(final String token) {
        try {
            final String email = Jwts.parserBuilder()
                    .setSigningKey(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return this.cryptoAES.decrypt(email, this.jwtProperties.getSecret());
        } catch (Exception e) {
            log.error("JWT token is expired or invalid");
            throw new JwtTokenException();
        }
    }

    public Authentication getAuthentication(final String token) {
        final String email = getEmailFromToken(token);
        final JwtUser jwtUser = this.userService.findByEmail(email)
                .map(this.jwtUserMapper::asJwtUser)
                .orElseThrow(() -> {
                    log.error("Can't found employee by email");
                    return new AuthenticationException();
                });
        return new UsernamePasswordAuthenticationToken(jwtUser.getEmail(), jwtUser.getPassword(), jwtUser.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        return fetchToken(req.getHeader(AUTHORIZATION));
    }

    private String fetchToken(final String bearerToken) {
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(this.jwtProperties.getBearer())) {
            return bearerToken.substring(this.jwtProperties.getBearer().length());
        }
        return null;
    }
}
