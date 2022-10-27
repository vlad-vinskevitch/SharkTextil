package com.shark.textil.infrastructure.jpa.repository;

import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM UserEntity user WHERE user.email = :email")
    UserEntity getByEmail(String email);

    @Query("SELECT user FROM UserEntity user WHERE user.email = :email")
    Optional<UserEntity> findByEmail(String email);
}
