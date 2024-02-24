package com.ambrosegoh.database.Domain;

public interface IAccountFactory {
    IAccount create(int accountId, String username, String password);
}
