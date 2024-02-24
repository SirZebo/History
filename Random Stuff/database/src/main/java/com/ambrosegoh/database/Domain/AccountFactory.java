package com.ambrosegoh.database.Domain;

public class AccountFactory implements IAccountFactory {
    @Override
    public IAccount create(int accountId, String username, String password) {
        return new Account(accountId, username, password);
    }
}
