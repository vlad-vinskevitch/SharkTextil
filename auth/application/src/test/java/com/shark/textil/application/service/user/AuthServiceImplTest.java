package com.shark.textil.application.service.user;

import com.shark.textil.application.exception.UserActionException;
import com.shark.textil.domain.auth.AuthRequest;
import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.JwtTokenProviderService;
import com.shark.textil.service.security.AuthService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AuthServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public AuthService authService(UserRepository userRepository,
                                       PasswordEncoder passwordEncoder,
                                       JwtTokenProviderService tokenProviderService) {
            return new AuthServiceImpl(userRepository, passwordEncoder, tokenProviderService);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtTokenProviderService tokenProviderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Test
    void givenAuthRequest_whenAuthorize_thenReturnUser() {
        final AuthRequest authRequest = this.givenAuthRequest("password");
        final User user = givenUser();
        final String email = "email";

        when(this.userRepository.getByEmail(email)).thenReturn(user);

        final User actual = this.authService.authorize(authRequest);

        assertThat(actual).isEqualTo(user);
        verify(this.userRepository).getByEmail(email);
    }

    @Test
    void givenAuthRequest_whenAuthorize_thenThrowException() {
        final AuthRequest authRequest = this.givenAuthRequest("password1");
        final User user = givenUser();
        final String email = "email";

        when(this.userRepository.getByEmail(email)).thenReturn(user);

        assertThrows(UserActionException.class, () -> {
            this.authService.authorize(authRequest);
        });

        verify(this.userRepository).getByEmail(email);
    }

    private AuthRequest givenAuthRequest(final String password) {
        return AuthRequest.builder()
                .password(password)
                .email("email")
                .build();
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(List.of(UserRole.USER))
                .userName("userName")
                .userLastName("userLastName")
                .email("email")
                .password("$2a$10$2sBCo7VN1E9N9gtc4Wiz1uOuCJ3dA6ZtYr/ndEsoUK8m3KALK5qRq")
                .build();
    }
}