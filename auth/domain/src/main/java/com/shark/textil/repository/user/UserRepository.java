package com.shark.textil.repository.user;

import com.shark.textil.domain.user.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User getByEmail(String email);
}
