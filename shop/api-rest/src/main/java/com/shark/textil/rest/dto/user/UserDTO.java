package com.shark.textil.rest.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private UserStatusDTO userStatus;
    private UserRoleDTO userRole;
    private String email;
    private String password;
    private String userName;
    private String userLastName;
}