package com.ambrosegoh.database.Service;

import com.ambrosegoh.database.Domain.IAccount;

import java.math.BigDecimal;

public interface IAccountGateway {

    void save(IAccount account);

    boolean existsById(int accountId);

    boolean existsByUsername(String username);

    IAccount getAccount(String username);

    IAccount getAccount(int accountId);

    long getTotalAccounts();

    BigDecimal getTotalAccountValue();
}
