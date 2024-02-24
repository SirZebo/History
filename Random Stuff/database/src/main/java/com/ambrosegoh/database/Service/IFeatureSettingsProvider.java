package com.ambrosegoh.database.Service;

public interface IFeatureSettingsProvider {

    boolean isLoanEnabled(int accountId);

    boolean isCreditCardEnabled(int accountId);

    void save(int accountId, String feature, boolean state);

    boolean existsById(int accountId);
}
