package com.shark.textil.rest.dto.auth;

import com.shark.textil.rest.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private UserDTO userDTO;
    private AuthTokenDTO authTokenDTO;
}
