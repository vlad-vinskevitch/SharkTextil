package com.shark.textil.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private UserStatus userStatus;
    private String email;
    private String password;
    private String userName;
    private String userLastName;

    @Builder.Default
    private List<UserRole> authorities = new ArrayList<>();
}
