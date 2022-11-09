package com.shark.textil.rest.mapper;

import com.shark.textil.domain.auth.AuthRequest;
import com.shark.textil.rest.dto.auth.AuthRequestDTO;
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
class AuthRequestMapperImplTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public AuthRequestMapper authRequestMapper() {
            return new AuthRequestMapperImpl();
        }
    }

    @Autowired
    private AuthRequestMapper authRequestMapper;

    @Test
    void givenAuthRequestDTO_whenAsAuthRequest_thenReturnAuthRequest() {
        final AuthRequestDTO authRequestDTO = this.givenAuthRequestDTO();
        final AuthRequest authRequest = this.givenAuthRequest();

        final AuthRequest actual = this.authRequestMapper.asAuthRequest(authRequestDTO);

        assertThat(actual).isEqualTo(authRequest);
    }

    private AuthRequest givenAuthRequest() {
        return AuthRequest.builder()
                .email("email")
                .password("password")
                .build();
    }

    private AuthRequestDTO givenAuthRequestDTO() {
        return AuthRequestDTO.builder()
                .email("email")
                .password("password")
                .build();
    }

}