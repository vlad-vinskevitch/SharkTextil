package com.shark.textil.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum UserRole {

    USER(1L),
    ADMIN(2L);

    @Getter
    private final Long roleId;

    public static UserRole fromStatusId(final Long roleId) {
        return Stream.of(values())
                .filter(e -> e.roleId.equals(roleId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
