package com.AmbroseGoh.Bank.Application.Exception;

public class InvalidPasswordException extends AuthenticationCustomException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
