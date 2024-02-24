package com.ambrosegoh.database.infrastructure;

import com.ambrosegoh.database.Domain.IAccount;
import com.ambrosegoh.database.Service.IAccountGateway;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Bank implements IAccountGateway {

    Map<String, IAccount> usernameToAccounts;
    Map<Integer, IAccount> accountIdToAccounts;

    public Bank() {
        this.usernameToAccounts = new HashMap<>();
        this.accountIdToAccounts = new HashMap<>();
    }

    public void save(IAccount account) {
        usernameToAccounts.put(account.getUsername(), account);
        accountIdToAccounts.put(account.getAccountId(), account);
    }

    public boolean existsById(int accountId) {
        return accountIdToAccounts.containsKey(accountId);
    }

    public boolean existsByUsername(String username) {
        return usernameToAccounts.containsKey(username);
    }

    public IAccount getAccount(String username) {
        return usernameToAccounts.get(username);
    }

    public IAccount getAccount(int accountId) {
        return accountIdToAccounts.get(accountId);
    }

    public long getTotalAccounts() {
        return accountIdToAccounts.size();
    }

    public BigDecimal getTotalAccountValue() {
        return accountIdToAccounts.values()
                .stream()
                .map(IAccount::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
