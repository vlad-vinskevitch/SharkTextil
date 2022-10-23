package com.shark.textil.infrastructure.mapper;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserRoleEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserStatusEntity;
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
class UserMapperImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public UserMapper userMapper() {
            return new UserMapperImpl();
        }
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    void givenUser_whenAsUserEntity_thenReturnUserEntity() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();

        final UserEntity actual = this.userMapper.asUserEntity(user);

        assertThat(actual).isEqualTo(userEntity);
    }

    @Test
    void givenUserEntity_whenAsUser_thenReturnUser() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();

        final User actual = this.userMapper.asUser(userEntity);

        assertThat(actual).isEqualTo(user);
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .userRole(UserRole.ADMIN)
                .userName("userName")
                .userLastName("userLastName")
                .build();
    }
    private UserEntity givenUserEntity() {
        return UserEntity.builder()
                .userId(1L)
                .userStatusEntity(UserStatusEntity.builder()
                        .userRoleId(1L)
                        .description("ACTIVE")
                        .build())
                .userRoleEntity(UserRoleEntity.builder()
                        .userRoleId(2L)
                        .description("ADMIN")
                        .build())
                .userName("userName")
                .userLastName("userLastName")
                .build();
    }
}