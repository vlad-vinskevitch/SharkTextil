package com.shark.textil.api.mapper;

import com.shark.textil.api.dto.StaffDTO;
import com.shark.textil.api.dto.StaffTypeDTO;
import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    List<StaffDTO> asStaffDTOs(List<Staff> src);

    StaffDTO asStaffDTO(Staff staff);

    Staff asStaff(StaffDTO staffDTO);

    StaffType asStaffType(StaffTypeDTO src);

}
