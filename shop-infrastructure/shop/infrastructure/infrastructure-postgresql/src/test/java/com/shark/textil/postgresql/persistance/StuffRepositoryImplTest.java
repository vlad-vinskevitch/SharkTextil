package com.shark.textil.postgresql.persistance;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffStatus;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.postgresql.entity.staff.StaffEntity;
import com.shark.textil.postgresql.entity.staff.StaffStatusEntity;
import com.shark.textil.postgresql.entity.staff.StaffTypeEntity;
import com.shark.textil.postgresql.jpa.StaffJPARepository;
import com.shark.textil.postgresql.mapper.StaffEntityMapper;
import com.shark.textil.repository.staff.StaffRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StuffRepositoryImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public StaffRepository staffRepository(final StaffJPARepository staffJPARepository,
                                               final StaffEntityMapper staffEntityMapper) {
            return new StuffRepositoryImpl(staffJPARepository,
                    staffEntityMapper);
        }
    }

    @Autowired
    private StaffRepository staffRepository;

    @MockBean
    private StaffJPARepository staffJPARepository;

    @MockBean
    private StaffEntityMapper staffEntityMapper;

    @Test
    void givenStaff_whenCreate_thenReturnCreatedStuff() {

        final Staff staff = this.givenStaff();
        final StaffEntity staffEntity = this.givenStaffEntity();

        when(this.staffEntityMapper.asStaff(staffEntity)).thenReturn(staff);
        when(this.staffEntityMapper.asStaffEntity(staff)).thenReturn(staffEntity);
        when(this.staffJPARepository.save(staffEntity)).thenReturn(staffEntity);

        final Staff actual = this.staffRepository.create(staff);

        assertThat(actual).isEqualTo(staff);

        verify(this.staffEntityMapper).asStaff(staffEntity);
        verify(this.staffJPARepository).save(staffEntity);
        verify(this.staffEntityMapper).asStaffEntity(staff);
    }

    @Test
    void givenStaffType_whenFindByType_thenReturnListOfStaff() {
        final StaffTypeEntity staffTypeEntity = mock(StaffTypeEntity.class);
        final StaffType staffType = mock(StaffType.class);
        final List<StaffEntity> staffEntities = List.of(this.givenStaffEntity());
        final List<Staff> staff = List.of(this.givenStaff());

        when(this.staffEntityMapper.asStaffTypeEntity(staffType)).thenReturn(staffTypeEntity);
        when(this.staffJPARepository.findByType(staffTypeEntity)).thenReturn(staffEntities);
        when(this.staffEntityMapper.asStaffList(staffEntities)).thenReturn(staff);

        final List<Staff> actual = this.staffRepository.findByType(staffType);

        assertThat(actual).isEqualTo(staff);

        verify(this.staffJPARepository).findByType(staffTypeEntity);
        verify(this.staffEntityMapper).asStaffTypeEntity(staffType);
        verify(this.staffEntityMapper).asStaffList(staffEntities);
    }

    private Staff givenStaff() {
        return Staff.builder()
                .id(1L)
                .staffStatus(StaffStatus.ACTIVE)
                .staffType(StaffType.TROUSERS)
                .description("description")
                .name("name")
                .color(2)
                .price(3D)
                .size("M")
                .build();
    }

    private StaffEntity givenStaffEntity() {
        return StaffEntity.builder()
                .id(1L)
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