package com.shark.textil.service;

import com.shark.textil.domain.user.User;

import java.util.Optional;

public interface UserService {

    User create(User user);

    User getByEmail(String email);

    Optional<User> findByEmail(String email);

}
