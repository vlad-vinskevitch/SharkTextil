package com.shark.textil.security.utils;

import com.shark.textil.domain.props.JwtProperties;
import com.shark.textil.domain.user.User;
import com.shark.textil.security.exception.JwtTokenException;
import com.shark.textil.service.JwtTokenProviderService;
import com.shark.textil.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtTokenProviderImpl implements JwtTokenProviderService {

    private final JwtProperties jwtProperties;
    private final UserService userService;
    private final CryptoAES cryptoAES;

    @Override
    public String createAccessToken(final User user) {
        final String email = this.cryptoAES.encrypt(user.getEmail(), this.jwtProperties.getSecret());
        final Claims claims = Jwts.claims()
                .setSubject(email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtProperties.getValidity().getTime()))
                .signWith(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                .compact();
    }

    @Override
    public String createRefreshToken(User user) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.jwtProperties.getValidity().getRefreshTime()))
                .setSubject(this.cryptoAES.encrypt(user.getEmail(), this.jwtProperties.getSecret()))
                .signWith(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                .compact();
    }

    public String resolveToken(final HttpServletRequest req) {
        return this.fetchToken(req.getHeader(AUTHORIZATION));
    }

    public boolean validateToken(String token) {
        try {
            final Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtTokenException(e.getMessage());
        }
    }

    public Authentication getAuthentication(String token) throws AuthenticationException {
        final String email = this.getEmailFromToken(token);
        final User employeeDto = this.userService.findByEmail(email)
                .orElseThrow(AuthenticationException::new);
        return new UsernamePasswordAuthenticationToken(employeeDto.getEmail(), employeeDto.getPassword());
    }

    public String getEmailFromToken(String token) {
        try {
            final String email = Jwts.parserBuilder()
                    .setSigningKey(this.cryptoAES.getSigningKey(this.jwtProperties.getSecret()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return this.cryptoAES.decrypt(email, jwtProperties.getSecret());
        } catch (Exception e) {
            throw new JwtTokenException(e.getMessage());
        }
    }

    private String fetchToken(String bearerToken) {
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(this.jwtProperties.getBearer())) {
            return bearerToken.substring(this.jwtProperties.getBearer().length());
        }
        return null;
    }


}
