package com.shark.textil.postgresql.persistance;

import com.shark.textil.domain.properties.staff.Staff;
import com.shark.textil.domain.properties.staff.StaffType;
import com.shark.textil.postgresql.entity.staff.StaffEntity;
import com.shark.textil.postgresql.entity.staff.StaffTypeEntity;
import com.shark.textil.postgresql.jpa.StaffJPARepository;
import com.shark.textil.postgresql.mapper.StaffEntityMapper;
import com.shark.textil.repository.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StuffRepositoryImpl implements StaffRepository {

    private final StaffJPARepository staffJPARepository;

    private final StaffEntityMapper staffEntityMapper;

    @Override
    public Staff create(Staff staff) {
        final StaffEntity staffEntity = this.staffEntityMapper.asStaffEntity(staff);
        final StaffEntity createdStaffEntity = this.staffJPARepository.save(staffEntity);
        return this.staffEntityMapper.asStaff(createdStaffEntity);
    }

    @Override
    public List<Staff> findByType(StaffType staffType) {
        final StaffTypeEntity staffTypeEntity = this.staffEntityMapper.asStaffTypeEntity(staffType);
        final List<StaffEntity> staffEntities = this.staffJPARepository.findByType(staffTypeEntity);
        return this.staffEntityMapper.asStaffList(staffEntities);
    }
}
