package com.shark.textil.service;

import com.shark.textil.domain.user.User;

public interface JwtTokenProviderService {

    String createAccessToken(User user);

    String createRefreshToken(User user);

    boolean validateToken(String token);

    String getEmailFromToken(String token);
}
