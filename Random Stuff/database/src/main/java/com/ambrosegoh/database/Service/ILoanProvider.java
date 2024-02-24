package com.ambrosegoh.database.Service;

/* 1. Maybe only 1 loan allowed?
 * 2. Loan cannot exceed 50% of account balance
 */
public interface ILoanProvider {

    // if the loan is great
    void requestLoan();

    void getLoan(int accountId);

    void payLoan();
}
