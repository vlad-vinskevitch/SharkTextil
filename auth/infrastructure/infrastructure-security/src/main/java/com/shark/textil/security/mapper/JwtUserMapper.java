package com.shark.textil.security.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.security.jwt.JwtUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JwtUserMapper {

    @Mapping(expression = "java(asGrandAuthorities(user.getAuthorities()))", target = "authorities")
    JwtUser asJwtUser(User user);

    default List<GrantedAuthority> asGrandAuthorities(final List<UserRole> authorities) {
        return authorities.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.name()))
                .collect(Collectors.toList());
    }
}
