package com.shark.textil.infrastructure.persistence;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.infrastructure.jpa.entity.user.UserEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserRoleEntity;
import com.shark.textil.infrastructure.jpa.entity.user.UserStatusEntity;
import com.shark.textil.infrastructure.jpa.repository.UserJPARepository;
import com.shark.textil.infrastructure.mapper.UserMapper;
import com.shark.textil.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserRepositoryImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public UserRepository userRepository(UserJPARepository repository,
                                             UserMapper mapper) {
            return new UserRepositoryImpl(repository, mapper);
        }
    }

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserJPARepository jpaRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void givenUser_whenSave_thenReturnUser() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();

        when(this.userMapper.asUserEntity(user)).thenReturn(userEntity);
        when(this.jpaRepository.save(userEntity)).thenReturn(userEntity);
        when(this.userMapper.asUser(userEntity)).thenReturn(user);

        final User actual = this.userRepository.save(user);

        assertThat(actual).isEqualTo(user);

        verify(this.userMapper).asUser(userEntity);
        verify(this.userMapper).asUserEntity(user);
        verify(this.jpaRepository).save(userEntity);
    }

    @Test
    void givenEmail_whenGetByEmail_thenReturnUser() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();
        final String email = "email";

        when(this.userMapper.asUser(userEntity)).thenReturn(user);
        when(this.jpaRepository.getByEmail(email)).thenReturn(userEntity);

        final User actual = this.userRepository.getByEmail(email);

        assertThat(actual).isEqualTo(user);

        verify(this.userMapper).asUser(userEntity);
        verify(this.jpaRepository).getByEmail(email);
    }

    @Test
    void givenEmail_whenFindUserByEmail_thenReturnUser() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();
        final String email = "email";

        when(this.userMapper.asUser(userEntity)).thenReturn(user);
        when(this.jpaRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));

        final Optional<User> actual = this.userRepository.findByEmail(email);

        assertThat(actual).isNotEmpty();

        verify(this.userMapper).asUser(userEntity);
        verify(this.jpaRepository).findByEmail(email);
    }

    @Test
    void givenEmail_whenFindUserByEmail_thenReturnEmpty() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();
        final String email = "email";

        when(this.userMapper.asUser(userEntity)).thenReturn(user);
        when(this.jpaRepository.findByEmail(email)).thenReturn(Optional.empty());

        final Optional<User> actual = this.userRepository.findByEmail(email);

        assertThat(actual).isEmpty();

        verify(this.userMapper, never()).asUser(userEntity);
        verify(this.jpaRepository).findByEmail(email);
    }

    @Test
    void givenUserId_whenFindById_thenReturnUser() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();
        final Long userId = 1L;

        when(this.userMapper.asUser(userEntity)).thenReturn(user);
        when(this.jpaRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        final Optional<User> actual = this.userRepository.findById(userId);

        assertThat(actual).isNotEmpty();

        verify(this.userMapper).asUser(userEntity);
        verify(this.jpaRepository).findById(userId);
    }

    @Test
    void givenUserId_whenFindById_thenReturnEmpty() {
        final User user = this.givenUser();
        final UserEntity userEntity = this.givenUserEntity();
        final Long userId = 1L;

        when(this.userMapper.asUser(userEntity)).thenReturn(user);
        when(this.jpaRepository.findById(userId)).thenReturn(Optional.empty());

        final Optional<User> actual = this.userRepository.findById(userId);

        assertThat(actual).isEmpty();

        verify(this.userMapper, never()).asUser(userEntity);
        verify(this.jpaRepository).findById(userId);
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(List.of(UserRole.USER))
                .email("email")
                .password("password")
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
                .authoritiesEntity(List.of(UserRoleEntity.builder()
                        .roleId(1L)
                        .description("USER")
                        .build()))
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("password")
                .build();
    }
}