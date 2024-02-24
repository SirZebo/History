package com.ambrosegoh.database.Domain;

import java.math.BigDecimal;

public interface IAccount {

    boolean isTransferValid(BigDecimal amt);

    boolean isUserNameValid(String username);

    boolean isPasswordValid(String password);

    boolean isPasswordFormatValid(String password);

    boolean isBalanceAmtValid(BigDecimal amt);

    boolean isOverseasWithdrawLimitValid(BigDecimal amt);

    int getAccountId();

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    BigDecimal getBalance();

    void setBalance(BigDecimal balance);

    BigDecimal getTransferLimit();

    void setTransferLimit(BigDecimal transferLimit);

    BigDecimal getOverseasWithdrawLimit();

    void setOverseasWithdrawLimit(BigDecimal overseasWithdrawLimit);
}
