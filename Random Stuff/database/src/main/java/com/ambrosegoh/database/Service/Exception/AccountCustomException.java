package com.ambrosegoh.database.Service.Exception;

public class AccountCustomException extends Exception {

    public AccountCustomException() {

    }

    public AccountCustomException(String message) {
        super(message);
    }

    public AccountCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
