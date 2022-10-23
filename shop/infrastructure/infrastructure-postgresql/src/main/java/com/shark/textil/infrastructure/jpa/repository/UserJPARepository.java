package com.shark.textil.infrastructure.jpa.repository;

import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<UserEntity, Long> {
}
