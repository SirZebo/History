package com.AmbroseGoh.Bank.Application.Exception;

public class AuthenticationCustomException extends Exception {
    public AuthenticationCustomException() {

    }

    public AuthenticationCustomException(String message) {
        super(message);
    }

    public AuthenticationCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
