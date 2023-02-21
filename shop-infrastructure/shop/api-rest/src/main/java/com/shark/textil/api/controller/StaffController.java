package com.shark.textil.api.controller;

import com.shark.textil.api.dto.StaffDTO;
import com.shark.textil.api.dto.StaffTypeDTO;
import com.shark.textil.api.mapper.StaffMapper;
import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    private final StaffMapper staffMapper;

    @PostMapping("/create")
    public ResponseEntity<StaffDTO> create(@RequestBody StaffDTO staffDTO) {
        final Staff staff = this.staffService.create(this.staffMapper.asStaff(staffDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.staffMapper.asStaffDTO(staff));
    }

    @GetMapping("/{stuffType}")
    public List<StaffDTO> findByType(@PathVariable StaffTypeDTO stuffType){
        final List<Staff> staffList = this.staffService.findByType(this.staffMapper.asStaffType(stuffType));
        return this.staffMapper.asStaffDTOs(staffList);
    }
}
