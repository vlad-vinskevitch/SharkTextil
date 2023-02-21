package com.shark.textil.api.controller;

import com.shark.textil.api.dto.StaffDTO;
import com.shark.textil.api.dto.StaffStatusDTO;
import com.shark.textil.api.dto.StaffTypeDTO;
import com.shark.textil.api.mapper.StaffMapper;
import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffStatus;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.service.StaffService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StaffControllerTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public StaffController staffController(final StaffService staffService,
                                           final StaffMapper staffMapper) {
            return new StaffController(staffService, staffMapper);
        }
    }

    @Autowired
    private StaffController staffController;

    @MockBean
    private StaffService staffService;

    @MockBean
    private StaffMapper staffMapper;

    @Test
    void givenStaffDTO_whenCreate_thenReturnSuccess() {
        final Staff staff = this.givenStaff();
        final StaffDTO staffDTO = this.givenStaffDTO();

        when(this.staffMapper.asStaff(staffDTO)).thenReturn(staff);
        when(this.staffMapper.asStaffDTO(staff)).thenReturn(staffDTO);
        when(this.staffService.create(staff)).thenReturn(staff);

        final ResponseEntity<StaffDTO> actual = this.staffController.create(staffDTO);

        assertThat(actual).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(staffDTO));

        verify(this.staffMapper).asStaff(staffDTO);
        verify(this.staffMapper).asStaffDTO(staff);
        verify(this.staffService).create(staff);
    }

    @Test
    void givenStaffType_whenFindByType_thenReturnListOfStaff(){
        final List<StaffDTO> staffDTOS = List.of(this.givenStaffDTO());
        final List<Staff> staffList = List.of(this.givenStaff());
        final StaffTypeDTO staffTypeDTO = StaffTypeDTO.TROUSERS;
        final StaffType staffType = StaffType.TROUSERS;

        when(this.staffMapper.asStaffType(staffTypeDTO)).thenReturn(staffType);
        when(this.staffMapper.asStaffDTOs(staffList)).thenReturn(staffDTOS);
        when(this.staffService.findByType(staffType)).thenReturn(staffList);

        final List<StaffDTO> actual = this.staffController.findByType(staffTypeDTO);

        assertThat(actual).isEqualTo(staffDTOS);

        verify(this.staffMapper).asStaffDTOs(staffList);
        verify(this.staffMapper).asStaffType(staffTypeDTO);
        verify(this.staffService).findByType(staffType);
    }

    private Staff givenStaff(){
        return Staff.builder()
                .staffStatus(StaffStatus.ACTIVE)
                .staffType(StaffType.TROUSERS)
                .id(1L)
                .size("M")
                .price(10D)
                .color(2)
                .description("description")
                .name("name")
                .build();
    }

    private StaffDTO givenStaffDTO(){
        return StaffDTO.builder()
                .id(1L)
                .size("M")
                .price(10D)
                .color(2)
                .staffStatus(StaffStatusDTO.ACTIVE)
                .staffType(StaffTypeDTO.TROUSERS)
                .description("description")
                .name("name")
                .build();
    }

}