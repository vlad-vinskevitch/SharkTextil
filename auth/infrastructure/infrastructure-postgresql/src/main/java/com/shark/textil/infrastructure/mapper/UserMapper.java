package com.shark.textil.infrastructure.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserRoleEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(expression = "java(asUserStatus(entity.getUserStatusEntity()))", target = "userStatus")
    @Mapping(expression = "java(asUserRoles(entity.getAuthoritiesEntity()))", target = "authorities")
    User asUser(UserEntity entity);

    @Mapping(expression = "java(asUserStatusEntity(user.getUserStatus()))", target = "userStatusEntity")
    @Mapping(expression = "java(asUserRoleEntity(user.getAuthorities()))", target = "authoritiesEntity")
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

    default List<UserRole> asUserRoles(final List<UserRoleEntity> authorities) {
        return authorities.stream()
                .map(src -> UserRole.fromStatusId(src.getRoleId()))
                .collect(Collectors.toList());
    }

    default List<UserRoleEntity> asUserRoleEntity(final List<UserRole> authorities) {
        return authorities.stream()
                .map(src -> UserRoleEntity.builder()
                .roleId(src.getRoleId())
                .description(src.name().toUpperCase(Locale.ROOT))
                .build())
                .collect(Collectors.toList());
    }
}
