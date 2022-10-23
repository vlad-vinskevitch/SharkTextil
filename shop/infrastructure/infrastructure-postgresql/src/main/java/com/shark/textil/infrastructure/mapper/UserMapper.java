package com.shark.textil.infrastructure.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserRoleEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Locale;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(expression = "java(asUserStatus(entity.getUserStatusEntity()))", target = "userStatus")
    @Mapping(expression = "java(asUserRole(entity.getUserRoleEntity()))", target = "userRole")
    User asUser(UserEntity entity);

    @Mapping(expression = "java(asUserStatusEntity(user.getUserStatus()))", target = "userStatusEntity")
    @Mapping(expression = "java(asUserRoleEntity(user.getUserRole()))", target = "userRoleEntity")
    UserEntity asUserEntity(User user);

    default UserStatus asUserStatus(final UserStatusEntity src) {
        return UserStatus.fromStatusId(src.getUserRoleId());
    }

    default UserStatusEntity asUserStatusEntity(final UserStatus src) {
        return UserStatusEntity.builder()
                .userRoleId(src.getStatusId())
                .description(src.name().toUpperCase(Locale.ROOT))
                .build();
    }

    default UserRole asUserRole(final UserRoleEntity src) {
        return UserRole.fromStatusId(src.getUserRoleId());
    }

    default UserRoleEntity asUserRoleEntity(final UserRole src) {
        return UserRoleEntity.builder()
                .userRoleId(src.getRoleId())
                .description(src.name().toUpperCase(Locale.ROOT))
                .build();
    }
}
