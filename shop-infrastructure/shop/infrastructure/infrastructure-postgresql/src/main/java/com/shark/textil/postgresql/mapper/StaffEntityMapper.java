package com.shark.textil.postgresql.mapper;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffStatus;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.postgresql.entity.staff.StaffEntity;
import com.shark.textil.postgresql.entity.staff.StaffStatusEntity;
import com.shark.textil.postgresql.entity.staff.StaffTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffEntityMapper {

    List<Staff> asStaffList(List<StaffEntity> src);

    Staff asStaff(StaffEntity src);

    StaffEntity asStaffEntity(Staff src);

    default StaffType asStaffType(StaffTypeEntity src) {
        return StaffType.fromTypeId(src.getTypeId());
    }

    default StaffTypeEntity asStaffTypeEntity(StaffType src) {
        return StaffTypeEntity.builder()
                .typeId(src.getTypeId())
                .typeDescription(src.getDescription())
                .build();
    }

    default StaffStatus asStaffStatus(StaffStatusEntity src) {
        return StaffStatus.fromTypeId(src.getStatusId());
    }

    default StaffStatusEntity asStaffStatusEntity(StaffStatus src) {
        return StaffStatusEntity.builder()
                .statusId(src.getStatusId())
                .typeDescription(src.getDescription())
                .build();
    }

}
