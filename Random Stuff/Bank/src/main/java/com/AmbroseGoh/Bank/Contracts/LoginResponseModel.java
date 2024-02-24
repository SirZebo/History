package com.AmbroseGoh.Bank.Contracts;

import java.util.UUID;

public class LoginResponseModel {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String token;

    public LoginResponseModel(UUID id, String firstName, String lastName, String email, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

}
