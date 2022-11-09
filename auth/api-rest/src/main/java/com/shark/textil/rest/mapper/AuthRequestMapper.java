package com.shark.textil.rest.mapper;

import com.shark.textil.domain.auth.AuthRequest;
import com.shark.textil.rest.dto.auth.AuthRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRequestMapper {

    AuthRequest asAuthRequest(AuthRequestDTO authRequestDTO);
}
