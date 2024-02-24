package com.ambrosegoh.database.Service;

import com.ambrosegoh.database.Domain.IAccount;
import com.ambrosegoh.database.Service.Exception.AccountCustomException;
import com.ambrosegoh.database.Service.Exception.AccountDoesNotExistException;
import com.ambrosegoh.database.Service.Exception.InsufficientBalanceException;
import com.ambrosegoh.database.Service.Exception.TransferLimitExceededException;

import java.math.BigDecimal;

/* 1. Deposit
 * 2. Withdraw
 * 3. Transfer
 * 4. Get Balance
 * 5. Overseas withdraw
 *
 * Todo: third-party transfer, remain balance?
 * Maybe remaining balance is + loan
 */
public class AccountService {

    private final IAccountGateway gateway;

    public AccountService(IAccountGateway gateway) {
        this.gateway = gateway;
    }
    
    public void transfer(int src, int dst, BigDecimal amt) throws AccountCustomException {

        if (!gateway.existsById(src)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        if (!gateway.existsById(dst)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount srcAccount = gateway.getAccount(src);

        if (!srcAccount.isBalanceAmtValid(amt)) {
            throw new InsufficientBalanceException("This account has insufficient balance.");
        }

        if (!srcAccount.isTransferValid(amt)) {
            throw new TransferLimitExceededException("Transfer amount has exceeded this account's transfer limit.");
        }

        /*
        if(!security.runVerification()) {
            // throw FailedVerificationException();
        }
        */

        IAccount dstAccount = gateway.getAccount(dst);
        srcAccount.setBalance(srcAccount.getBalance().subtract(amt));
        dstAccount.setBalance(dstAccount.getBalance().add(amt));

        gateway.save(srcAccount);
        gateway.save(dstAccount);

        // return presenter.prepareSuccessView();

    }

    public void getAvailableBalance(int accountId, BigDecimal amt) throws AccountCustomException {

        if (!gateway.existsById(accountId)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount account = gateway.getAccount(accountId);
        BigDecimal availableBalance = account.getBalance();
        // return presenter.prepareSuccessView();
    }

    // Not too sure can deposit and withdraw
    public void deposit(int accountId, BigDecimal amt) throws AccountCustomException {

        if (!gateway.existsById(accountId)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount account = gateway.getAccount(accountId);
        account.setBalance(account.getBalance().add(amt));

        gateway.save(account);
        // return presenter.prepareSuccessView();
    }

    public void withdraw(int accountId, BigDecimal amt) throws AccountCustomException {

        if (!gateway.existsById(accountId)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount account = gateway.getAccount(accountId);

        if (!account.isBalanceAmtValid(amt)) {
            throw new InsufficientBalanceException("This account has insufficient balance.");
        }

        account.setBalance(account.getBalance().subtract(amt));

        gateway.save(account);
        // return presenter.prepareSuccessView();
    }

    public void overseasWithdraw(int accountId, BigDecimal amt) throws AccountCustomException {

        if (!gateway.existsById(accountId)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount account = gateway.getAccount(accountId);

        if (!account.isBalanceAmtValid(amt)) {
            throw new InsufficientBalanceException("This account has insufficient balance.");
        }

        if (!account.isOverseasWithdrawLimitValid(amt)) {
            throw new TransferLimitExceededException("Transfer amount has exceeded this account's overseas withdraw limit.");
        }

        account.setBalance(account.getBalance().subtract(amt));

        gateway.save(account);
        // return presenter.prepareSuccessView();
    }

    public void changePassword(int accountId, String password) throws AccountCustomException {

        if (!gateway.existsById(accountId)) {
            throw new AccountDoesNotExistException("Account does not exist.");
        }

        IAccount account = gateway.getAccount(accountId);

        account.setPassword(password);

        if (!account.isPasswordValid(password)) {
            //throw new InvalidPasswordException("Password is invalid");
        }

        if (!account.isPasswordFormatValid(password)) {
            //throw new WrongPasswordFormatException("Password does not contain the required format");
        }

        // Maybe new password cannot be the same as old password

        /*
        if(!security.runVerification()) {
            // throw FailedVerificationException();
        }
        */

        gateway.save(account);
        // return presenter.prepareSuccessView();
    }
}
