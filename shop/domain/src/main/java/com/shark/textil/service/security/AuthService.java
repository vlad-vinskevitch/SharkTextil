package com.shark.textil.service.security;

import com.shark.textil.domain.auth.AuthRequest;
import com.shark.textil.domain.user.User;

public interface AuthService {

    User authorize(AuthRequest authRequest);
}
