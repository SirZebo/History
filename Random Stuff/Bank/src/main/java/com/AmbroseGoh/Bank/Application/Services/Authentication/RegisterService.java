package com.AmbroseGoh.Bank.Application.Services.Authentication;

import com.AmbroseGoh.Bank.Application.Common.Interfaces.Authentication.IJwtTokenGenerator;
import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Application.Exception.EmailAlreadyExistsException;
import com.AmbroseGoh.Bank.Application.Exception.InvalidPasswordException;
import com.AmbroseGoh.Bank.Application.Presenter.Authentication.IRegisterAuthenticationPresenter;
import com.AmbroseGoh.Bank.Contracts.RegisterResponseModel;
import com.AmbroseGoh.Bank.Domain.ICustomer;
import com.AmbroseGoh.Bank.Domain.ICustomerFactory;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class RegisterService implements IRegisterService {

    private final IJwtTokenGenerator jwtTokenGenerator;
    private final ICustomerRegisterGateway gateway;
    private final IRegisterAuthenticationPresenter presenter;
    private final ICustomerFactory factory;

    // Dependency inject token generator
    public RegisterService(IJwtTokenGenerator jwtTokenGenerator, ICustomerRegisterGateway gateway, IRegisterAuthenticationPresenter presenter, ICustomerFactory factory) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.gateway = gateway;
        this.presenter = presenter;
        this.factory = factory;
    }

    // Can be change to RegisterRequestModel
    // Throw exception of AuthenticationResult
    public RegisterResponseModel register(String firstName, String lastName, String email, String password) throws AuthenticationCustomException {

        // Check if email / user already exists (gateway)
        if (gateway.existsByEmail(email)) {
            return presenter.prepareFailView(new EmailAlreadyExistsException("Email: " + email + " already exists"));
        }

        // Create user (generate unique ID) (Domain)
        ICustomer customer = factory.create();

        // Check if password is valid
        if (!customer.passwordIsValid()) {
            return presenter.prepareFailView(new InvalidPasswordException("Password is not valid"));
        }

        // Save customer in DB

        // Create JWT token (infra)
        UUID userId = randomUUID();

        var token = jwtTokenGenerator.generateToken(userId, firstName, lastName, -1);

        RegisterResponseModel responseModel = new RegisterResponseModel(userId, firstName, lastName, email, token);

        return presenter.prepareSuccessView(responseModel);
    }
}
