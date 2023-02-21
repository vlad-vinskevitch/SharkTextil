package com.shark.textil.application.service.stuff;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.repository.staff.StaffRepository;
import com.shark.textil.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StuffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public Staff create(Staff staff) {
        return staffRepository.create(staff);
    }

    @Override
    public List<Staff> findByType(StaffType staffType) {
        return this.staffRepository.findByType(staffType);
    }
}
