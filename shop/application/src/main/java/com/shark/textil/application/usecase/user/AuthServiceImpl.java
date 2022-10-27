package com.shark.textil.application.usecase.user;

import com.shark.textil.application.exception.UserActionException;
import com.shark.textil.domain.auth.AuthRequest;
import com.shark.textil.domain.user.User;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User authorize(AuthRequest authRequest) {
        final User user = userRepository.getByEmail(authRequest.getEmail());

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new UserActionException("Passwords not matches");
        }
        return user;
    }
}
