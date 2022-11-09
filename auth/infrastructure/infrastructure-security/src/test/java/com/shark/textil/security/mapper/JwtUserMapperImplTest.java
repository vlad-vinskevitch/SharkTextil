package com.shark.textil.security.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.security.jwt.JwtUser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JwtUserMapperImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public JwtUserMapper authRequestMapper() {
            return new JwtUserMapperImpl();
        }
    }

    @Autowired
    private JwtUserMapper jwtUserMapper;

    @Test
    void givenUser_whenAsUser_thenReturnJwtUser(){
        final User user = this.givenUser();
        final JwtUser jwtUser = this.givenJwtUser();

        final JwtUser actual = this.jwtUserMapper.asJwtUser(user);

        assertThat(actual).isEqualTo(jwtUser);
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(List.of(UserRole.USER))
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("password")
                .build();
    }

    private JwtUser givenJwtUser() {
        return JwtUser.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(List.of(new SimpleGrantedAuthority(UserRole.USER.name())))
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("password")
                .build();
    }
}