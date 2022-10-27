package com.shark.textil.security.exception;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String message) {
        super(message);
    }
}
