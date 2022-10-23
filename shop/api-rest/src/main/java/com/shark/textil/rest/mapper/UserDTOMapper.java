package com.shark.textil.rest.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.rest.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    User asUser(UserDTO userDTO);

    UserDTO asUserDTO(User userDTO);
}
