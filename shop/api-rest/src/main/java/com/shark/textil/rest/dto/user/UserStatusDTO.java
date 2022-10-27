package com.shark.textil.rest.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserStatusDTO {

    ACTIVE(1L),
    INACTIVE(2L),
    DELETED(3L);

    @Getter
    private final Long statusId;

}
