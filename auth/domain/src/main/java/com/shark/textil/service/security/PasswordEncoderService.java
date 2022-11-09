package com.shark.textil.service.security;

public interface PasswordEncoderService {

    String encode(CharSequence rawPassword);
}
