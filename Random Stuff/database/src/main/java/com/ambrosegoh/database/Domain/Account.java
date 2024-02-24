package com.ambrosegoh.database.Domain;

import java.math.BigDecimal;

public class Account implements IAccount {
    private final int accountId;
    private String username;
    private String password;
    private BigDecimal balance;
    private BigDecimal transferLimit;
    private BigDecimal overseasWithdrawLimit;

    public Account(int accountId, String username, String password) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.balance = BigDecimal.ZERO;
        this.transferLimit = BigDecimal.valueOf(5000);
        this.overseasWithdrawLimit = new BigDecimal(0);
    }

    @Override
    public boolean isUserNameValid(String username) {
        return this.username.equals(username);
    }

    @Override
    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean isPasswordFormatValid(String password) {
        throw new UnsupportedOperationException("To do");
    }

    @Override
    public boolean isBalanceAmtValid(BigDecimal amt) {
        return this.balance.compareTo(amt) >= 0;
    }

    @Override
    public boolean isTransferValid(BigDecimal amt) {
        return this.transferLimit.compareTo(amt) >= 0;
    }

    @Override
    public boolean isOverseasWithdrawLimitValid(BigDecimal amt) {
        return this.overseasWithdrawLimit.compareTo(amt) >= 0;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public BigDecimal getTransferLimit() {
        return transferLimit;
    }

    @Override
    public void setTransferLimit(BigDecimal transferLimit) {
        this.transferLimit = transferLimit;
    }

    @Override
    public BigDecimal getOverseasWithdrawLimit() {
        return overseasWithdrawLimit;
    }

    @Override
    public void setOverseasWithdrawLimit(BigDecimal overseasWithdrawLimit) {
        this.overseasWithdrawLimit = overseasWithdrawLimit;
    }
}
