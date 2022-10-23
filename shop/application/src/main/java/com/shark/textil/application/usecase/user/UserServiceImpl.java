package com.shark.textil.application.usecase.user;

import com.shark.textil.domain.user.User;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }
}
