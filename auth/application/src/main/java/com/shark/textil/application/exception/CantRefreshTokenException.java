package com.shark.textil.application.exception;

public class CantRefreshTokenException extends RuntimeException{

    public CantRefreshTokenException(String message) {
        super(message);
    }
}
