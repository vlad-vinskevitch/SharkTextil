package com.shark.textil.rest.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.rest.dto.auth.AuthResponseDTO;
import com.shark.textil.rest.dto.auth.AuthTokenDTO;
import com.shark.textil.service.JwtTokenProviderService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AuthResponseMapper {

    @Autowired
    protected UserDTOMapper userDTOMapper;

    @Autowired
    protected JwtTokenProviderService jwtTokenProviderService;

    @Mapping(expression = "java(userDTOMapper.asUserDTO(user))", target = "userDTO")
    @Mapping(expression = "java(asAuthTokenDTO(user))", target = "authTokenDTO")
    public abstract AuthResponseDTO asAuthResponseDTO(User user);

    @Mapping(expression = "java(jwtTokenProviderService.createAccessToken(user))", target = "accessToken")
    @Mapping(expression = "java(jwtTokenProviderService.createRefreshToken(user))", target = "refreshToken")
    public abstract AuthTokenDTO asAuthTokenDTO(User user);

}
