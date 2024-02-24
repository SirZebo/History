package com.ambrosegoh.database.Service.Exception;

public class InsufficientBalanceException extends AccountCustomException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
