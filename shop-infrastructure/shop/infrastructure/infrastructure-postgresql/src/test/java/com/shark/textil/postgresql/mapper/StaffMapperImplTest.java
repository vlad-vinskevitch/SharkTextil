package com.shark.textil.postgresql.mapper;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffStatus;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.postgresql.entity.staff.StaffEntity;
import com.shark.textil.postgresql.entity.staff.StaffStatusEntity;
import com.shark.textil.postgresql.entity.staff.StaffTypeEntity;
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
        public StaffEntityMapper authRequestMapper() {
            return new StaffEntityMapperImpl();
        }
    }

    @Autowired
    private StaffEntityMapper staffEntityMapper;

    @Test
    void givenStaffDTO_whenAsStaff_thenReturnStaff() {
        final Staff staff = this.givenStaff();
        final StaffEntity staffDTO = this.givenStaffEntity();

        final Staff actual = this.staffEntityMapper.asStaff(staffDTO);

        assertThat(actual).isEqualTo(staff);
    }

    @Test
    void givenStaff_whenAsStaffDTO_thenReturnStaffDTO() {
        final Staff staff = this.givenStaff();
        final StaffEntity staffDTO = this.givenStaffEntity();

        final StaffEntity actual = this.staffEntityMapper.asStaffEntity(staff);

        assertThat(actual).isEqualTo(staffDTO);
    }

    private Staff givenStaff(){
        return Staff.builder()
                .staffStatus(StaffStatus.ACTIVE)
                .staffType(StaffType.TROUSERS)
                .description("description")
                .name("name")
                .color(2)
                .price(3D)
                .size("M")
                .build();
    }

    private StaffEntity givenStaffEntity(){
        return StaffEntity.builder()
                .staffStatus(StaffStatusEntity.builder()
                        .statusId(1)
                        .build())
                .staffType(StaffTypeEntity.builder()
                        .typeId(1)
                        .build())
                .color(2)
                .price(3D)
                .size("M")
                .description("description")
                .name("name")
                .build();
    }
}