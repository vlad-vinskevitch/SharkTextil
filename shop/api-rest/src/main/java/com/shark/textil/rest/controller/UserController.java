package com.shark.textil.rest.controller;

import com.shark.textil.domain.user.User;
import com.shark.textil.rest.dto.auth.AuthRequestDTO;
import com.shark.textil.rest.dto.auth.AuthResponseDTO;
import com.shark.textil.rest.dto.user.UserDTO;
import com.shark.textil.rest.mapper.AuthRequestMapper;
import com.shark.textil.rest.mapper.UserDTOMapper;
import com.shark.textil.service.security.AuthService;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final AuthService authService;

    private final UserDTOMapper userDTOMapper;

    private final AuthRequestMapper requestMapper;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        final User user = this.userService.create(this.userDTOMapper.asUser(userDTO));
        return ResponseEntity.ok(this.userDTOMapper.asUserDTO(user));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO authRequestDTO) {
        this.authService.authorize(this.requestMapper.asAuthRequest(authRequestDTO));
        return ResponseEntity.ok(new AuthResponseDTO());
    }

}
