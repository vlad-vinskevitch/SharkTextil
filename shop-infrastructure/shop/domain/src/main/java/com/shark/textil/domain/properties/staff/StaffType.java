package com.shark.textil.domain.properties.staff;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum StaffType {

    TROUSERS(1, "TROUSERS"),
    T_SHIRT(2, "T_SHIRT");

    private final Integer typeId;
    private final String description;

    public static StaffType fromTypeId(Integer typeId) {
        return Stream.of(values())
                .filter(e -> e.typeId.equals(typeId))
                .findFirst()
                .get();
    }
}
