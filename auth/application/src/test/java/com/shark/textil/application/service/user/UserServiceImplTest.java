package com.shark.textil.application.service.user;

import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.domain.user.UserStatus;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.UserService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public UserService userRepository(UserRepository repository) {
            return new UserServiceImpl(repository);
        }
    }

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    void givenUser_whenCreate_thenReturnUser() {
        final User user = this.givenUser();

        when(userRepository.save(user)).thenReturn(user);

        final User actual = this.userService.create(user);

        assertThat(actual).isEqualTo(user);
        verify(userRepository).save(user);
    }

    private User givenUser() {
        return User.builder()
                .userId(1L)
                .userStatus(UserStatus.ACTIVE)
                .authorities(List.of(UserRole.USER))
                .userName("userName")
                .userLastName("userLastName")
                .build();
    }
}