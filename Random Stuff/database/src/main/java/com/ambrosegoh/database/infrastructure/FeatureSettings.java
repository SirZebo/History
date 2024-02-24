package com.ambrosegoh.database.infrastructure;

import com.ambrosegoh.database.Service.IFeatureSettingsProvider;

import java.util.HashMap;
import java.util.Map;

public class FeatureSettings implements IFeatureSettingsProvider {

    Map<Integer, Map<String, Boolean>> settings;

    public FeatureSettings() {
        this.settings = new HashMap<>();
    }

    // Probably need to use the abstract factory pattern
    @Override
    public boolean isLoanEnabled(int accountId) {

        if (existsById(accountId)) {
            return false;
        }

        Map<String, Boolean> accountSettings = settings.get(accountId);
        return accountSettings.getOrDefault("Loan", false);
    }

    @Override
    public boolean isCreditCardEnabled(int accountId) {

        if (existsById(accountId)) {
            return false;
        }

        Map<String, Boolean> accountSettings = settings.get(accountId);
        return accountSettings.getOrDefault("CreditCard", false);
    }

    @Override
    public void save(int accountId, String feature, boolean state) {

        settings.computeIfAbsent(accountId, a -> new HashMap<>());

        Map<String, Boolean> accountSettings = settings.get(accountId);
        accountSettings.put(feature, state);
    }

    @Override
    public boolean existsById(int accountId) {
        return settings.containsKey(accountId);
    }
}
