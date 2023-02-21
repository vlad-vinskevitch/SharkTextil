package com.shark.textil.postgresql.jpa;

import com.shark.textil.postgresql.entity.staff.StaffEntity;
import com.shark.textil.postgresql.entity.staff.StaffTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffJPARepository extends JpaRepository<StaffEntity, Long> {

    @Query("SELECT staff FROM StaffEntity staff " +
            "WHERE staff.staffType = :staffType")
    List<StaffEntity> findByType(StaffTypeEntity staffType);

}
