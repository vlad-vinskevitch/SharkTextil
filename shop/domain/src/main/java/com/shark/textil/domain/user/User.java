package com.shark.textil.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userId;
    private UserStatus userStatus;
    private UserRole userRole;
    private String userName;
    private String userLastName;
}
