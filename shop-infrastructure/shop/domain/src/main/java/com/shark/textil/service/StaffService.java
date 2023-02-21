package com.shark.textil.service;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;

import java.util.List;

public interface StaffService {

    Staff create(Staff staff);

    List<Staff> findByType(StaffType staffType);
}
