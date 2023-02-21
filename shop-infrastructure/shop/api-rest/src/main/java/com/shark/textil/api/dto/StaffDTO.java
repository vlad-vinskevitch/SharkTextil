package com.shark.textil.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDTO {

     private Long id;
     private String name;
     private String description;
     private String size;
     private Double price;
     private Integer color;
     private StaffTypeDTO staffType;
     private StaffStatusDTO staffStatus;
}
