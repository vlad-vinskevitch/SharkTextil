package com.shark.textil.rest.controller;

import com.shark.textil.domain.user.User;
import com.shark.textil.rest.dto.auth.AuthRequestDTO;
import com.shark.textil.rest.dto.auth.AuthResponseDTO;
import com.shark.textil.rest.dto.auth.AuthTokenDTO;
import com.shark.textil.rest.dto.user.UserDTO;
import com.shark.textil.rest.mapper.AuthRequestMapper;
import com.shark.textil.rest.mapper.AuthResponseMapper;
import com.shark.textil.rest.mapper.UserDTOMapper;
import com.shark.textil.service.security.AuthService;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final UserService userService;

    private final AuthService authService;

    private final UserDTOMapper userDTOMapper;

    private final AuthRequestMapper authRequestMapper;

    private final AuthResponseMapper authResponseMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        log.info("Received request to register user: {}", userDTO);

        final User user = this.userService.create(this.userDTOMapper.asUser(userDTO));
        return ResponseEntity.ok(this.userDTOMapper.asUserDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO authRequestDTO) {
        log.info("Received request to login into employee: {}", authRequestDTO);
        final AuthResponseDTO authResponseDTO = this.authResponseMapper.asAuthResponseDTO(this.authService.authorize(this.authRequestMapper.asAuthRequest(authRequestDTO)));
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/refreshToken")
    public AuthTokenDTO refreshToken(@RequestBody AuthTokenDTO authTokenDTO) {
        log.info("Received request to refresh token: {}", authTokenDTO);
        return this.authResponseMapper.asAuthTokenDTO(this.authService.refreshToken(authTokenDTO.getRefreshToken()));
    }

}
