package com.ambrosegoh.database.Service.Exception;

public class AccountDoesNotExistException extends AccountCustomException {

    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
