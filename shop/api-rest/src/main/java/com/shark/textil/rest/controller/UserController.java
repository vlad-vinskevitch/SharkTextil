package com.shark.textil.rest.controller;

import com.shark.textil.domain.user.User;
import com.shark.textil.rest.dto.UserDTO;
import com.shark.textil.rest.mapper.UserDTOMapper;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserDTOMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        final User user = this.userService.create(this.mapper.asUser(userDTO));
        return ResponseEntity.ok(this.mapper.asUserDTO(user));
    }
}
