package com.shark.textil.rest.controller;

import com.shark.textil.domain.user.UserRole;
import com.shark.textil.service.security.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adminService;

    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";

    @PostMapping("/role/{userId}")
    public ResponseEntity<Void> setRole(@PathVariable(USER_ID) Long userId, @RequestHeader(USER_ROLE) List<UserRole> userRole) {
        this.adminService.setPermission(userId, userRole);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public String get(){
        return "hello";
    }
}
