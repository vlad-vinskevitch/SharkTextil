package com.shark.textil.application.usecase.user;

import com.shark.textil.domain.user.User;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.getByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
