package com.shark.textil.repository.staff;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;

import java.util.List;

public interface StaffRepository {

    Staff create(Staff staff);

    List<Staff> findByType(StaffType staffType);
}
