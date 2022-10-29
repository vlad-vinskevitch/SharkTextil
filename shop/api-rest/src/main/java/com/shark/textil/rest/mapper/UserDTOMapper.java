package com.shark.textil.rest.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.rest.dto.user.UserDTO;
import com.shark.textil.service.security.PasswordEncoderService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserDTOMapper {

    @Autowired
    protected PasswordEncoderService passwordEncoderService;

    @Mapping(expression = "java(passwordEncoderService.encode(userDTO.getPassword()))", target = "password")
    @Mapping(expression = "java(setUserRoles())", target = "authorities")
    @Mapping(expression = "java(setUserStatus())", target = "userStatus")
    public abstract User asUser(UserDTO userDTO);

    public abstract UserDTO asUserDTO(User userDTO);

    protected List<UserRole> setUserRoles(){
        return List.of(UserRole.USER);
    }

    protected UserStatus setUserStatus(){
        return UserStatus.ACTIVE;
    }
}
