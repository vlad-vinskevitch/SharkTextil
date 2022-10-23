package com.shark.textil.rest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserRoleDTO {

    USER(1L),
    ADMIN(2L);

    @Getter
    private final Long roleId;

}
