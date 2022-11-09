package com.shark.textil.rest.controller;

import com.shark.textil.domain.user.User;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    private static final String USER_ID = "userId";

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable(USER_ID) Long userId) {
        return ResponseEntity.ok(this.userService.findById(userId));
    }

    @GetMapping("/get")
    public String get(){
        return "hello";
    }
}
