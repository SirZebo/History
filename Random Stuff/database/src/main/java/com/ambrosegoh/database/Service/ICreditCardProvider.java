package com.ambrosegoh.database.Service;

/* 1. if Credit card balance is valid
 * 2. Credit declined scenario / cancelled
 *
 */
public interface ICreditCardProvider {

    void save();

    void getCreditCard();

    boolean existsById();

}
