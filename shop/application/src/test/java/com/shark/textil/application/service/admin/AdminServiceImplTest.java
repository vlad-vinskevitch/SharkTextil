package com.shark.textil.application.service.admin;

import com.shark.textil.application.exception.UserNotFoundException;
import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.security.AdminService;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public AdminService userRepository(UserRepository repository) {
            return new AdminServiceImpl(repository);
        }
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AdminService adminService;

    @Test
    void givenUserId_whenSetPermission_thenUpdateUser() {
        final List<UserRole> roles = List.of(UserRole.USER, UserRole.ADMIN);
        final User updatedUser = this.givenUser(roles);
        final User user = this.givenUser(List.of(UserRole.USER));
        final Long userId = 1L;

        when(this.userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(this.userRepository.save(user)).thenReturn(user);

        this.adminService.setPermission(userId, roles);

        verify(this.userRepository).findById(userId);
        verify(this.userRepository).save(updatedUser);
    }

    @Test
    void givenUserId_whenSetPermission_thenThrowUserNotFoundException() {
        final List<UserRole> roles = List.of(UserRole.USER, UserRole.ADMIN);
        final User updatedUser = this.givenUser(roles);
        final Long userId = 1L;

        when(this.userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> this.adminService.setPermission(userId, roles));

        verify(this.userRepository).findById(userId);
        verify(this.userRepository, never()).save(updatedUser);
    }

    private User givenUser(List<UserRole> userRoles) {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(userRoles)
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("$2a$10$2sBCo7VN1E9N9gtc4Wiz1uOuCJ3dA6ZtYr/ndEsoUK8m3KALK5qRq")
                .build();
    }
}