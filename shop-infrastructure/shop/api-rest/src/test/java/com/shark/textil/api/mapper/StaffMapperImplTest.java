package com.shark.textil.api.mapper;

import com.shark.textil.api.dto.StaffDTO;
import com.shark.textil.api.dto.StaffStatusDTO;
import com.shark.textil.api.dto.StaffTypeDTO;
import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffStatus;
import com.shark.textil.domain.properties.staff.StaffType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StaffMapperImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public StaffMapper staffMapper() {
            return new StaffMapperImpl();
        }
    }

    @Autowired
    private StaffMapper staffMapper;

    @Test
    void givenStaffDTO_whenAsStaff_thenReturnStaff() {
        final Staff staff = this.givenStaff();
        final StaffDTO staffDTO = this.givenStaffDTO();

        final Staff actual = this.staffMapper.asStaff(staffDTO);

        assertThat(actual).isEqualTo(staff);
    }

    @Test
    void givenStaff_whenAsStaffDTO_thenReturnStaffDTO() {
        final Staff staff = this.givenStaff();
        final StaffDTO staffDTO = this.givenStaffDTO();

        final StaffDTO actual = this.staffMapper.asStaffDTO(staff);

        assertThat(actual).isEqualTo(staffDTO);
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