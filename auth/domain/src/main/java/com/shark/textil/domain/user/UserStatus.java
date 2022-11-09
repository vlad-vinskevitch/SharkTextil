package com.shark.textil.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
public enum UserStatus {

    ACTIVE(1L),
    INACTIVE(2L),
    DELETED(3L);

    @Getter
    private final Long statusId;

    public static UserStatus fromStatusId(final Long statusId) {
        return Stream.of(values())
                .filter(e -> e.statusId.equals(statusId))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
