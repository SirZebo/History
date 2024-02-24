package com.AmbroseGoh.Bank.Application.Exception;

public class EmailAlreadyExistsException extends AuthenticationCustomException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
