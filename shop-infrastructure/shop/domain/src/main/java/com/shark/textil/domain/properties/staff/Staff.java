package com.shark.textil.domain.properties.staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Staff {

    private Long id;
    private String name;
    private String description;
    private String size;
    private Double price;
    private Integer color;
    private StaffType staffType;
    private StaffStatus staffStatus;
}
