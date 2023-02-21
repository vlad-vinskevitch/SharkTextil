package com.shark.textil.domain.properties.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum StaffStatus {
    ACTIVE(1, "ACTIVE"),
    MANUAL_DELETED(2, "MANUAL_DELETED");

    private final Integer statusId;
    private final String description;

    public static StaffStatus fromTypeId(Integer statusId) {
        return Stream.of(values())
                .filter(e -> e.statusId.equals(statusId))
                .findFirst()
                .get();
    }
}
