package com.shark.textil.application.service.stuff;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.repository.staff.StaffRepository;
import com.shark.textil.service.StaffService;
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
class StuffServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public StaffService staffService(final StaffRepository staffRepository) {
            return new StuffServiceImpl(staffRepository);
        }
    }

    @Autowired
    private StaffService staffService;

    @MockBean
    private StaffRepository staffRepository;

    @Test
    void givenStaff_whenCreate_thenReturnCreatedStaff(){
        final Staff staff = mock(Staff.class);

        when(this.staffRepository.create(staff)).thenReturn(staff);

        final Staff actual = this.staffService.create(staff);

        assertThat(actual).isEqualTo(staff);

        verify(this.staffRepository).create(staff);
    }

    @Test
    void givenStaff_whenFindByType_thenReturnStaff() {
        final List<Staff> staff = List.of(mock(Staff.class));
        final StaffType staffType = StaffType.TROUSERS;

        when(this.staffRepository.findByType(staffType)).thenReturn(staff);

        final List<Staff> actual = this.staffService.findByType(staffType);

        assertThat(actual).isEqualTo(staff);

        verify(this.staffRepository).findByType(staffType);
    }

}