package com.shark.textil.application.service.admin;

import com.shark.textil.application.exception.UserNotFoundException;
import com.shark.textil.domain.user.User;
import com.shark.textil.domain.user.UserRole;
import com.shark.textil.repository.user.UserRepository;
import com.shark.textil.service.security.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public void setPermission(final Long targetUserId, final List<UserRole> userRole) {
        final User foundUser = this.userRepository.findById(targetUserId)
                .map(user -> {
                    user.setAuthorities(userRole);
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException("User with such id not found"));
        this.userRepository.save(foundUser);
    }
}
