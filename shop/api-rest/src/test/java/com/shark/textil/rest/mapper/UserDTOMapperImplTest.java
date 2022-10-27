package com.shark.textil.rest.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.rest.dto.user.UserDTO;
import com.shark.textil.rest.dto.user.UserRoleDTO;
import com.shark.textil.rest.dto.user.UserStatusDTO;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserDTOMapperImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public UserDTOMapper userDTOMapper() {
            return new UserDTOMapperImpl();
        }
    }

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Test
    void givenUserDTO_whenAsUser_thenReturnUser() {
        final User user = this.givenUser();
        final UserDTO userDTO = this.givenUserDTO();

        final User actual = this.userDTOMapper.asUser(userDTO);

        assertThat(actual).isEqualTo(user);
    }

    @Test
    void givenUser_whenAsUserDTO_thenReturnUserDTO() {
        final User user = this.givenUser();
        final UserDTO userDTO = this.givenUserDTO();

        final UserDTO actual = this.userDTOMapper.asUserDTO(user);

        assertThat(actual).isEqualTo(userDTO);
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .userRole(UserRole.ADMIN)
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("password")
                .build();
    }

    private UserDTO givenUserDTO() {
        return UserDTO.builder()
                .userId(1L)
                .userStatus(UserStatusDTO.ACTIVE)
                .userRole(UserRoleDTO.ADMIN)
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("password")
                .build();
    }
}