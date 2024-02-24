package com.ambrosegoh.database.Service.Exception;

public class TransferLimitExceededException extends AccountCustomException {

    public TransferLimitExceededException(String message) {
        super(message);
    }
}
