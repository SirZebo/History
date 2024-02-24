package com.AmbroseGoh.Bank.Application.Services.Authentication;

import com.AmbroseGoh.Bank.Application.Common.Interfaces.Authentication.IJwtTokenGenerator;
import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Application.Presenter.Authentication.ILoginAuthenticationPresenter;
import com.AmbroseGoh.Bank.Contracts.LoginResponseModel;

import static java.util.UUID.randomUUID;

public class LoginService implements ILoginService {

    private final IJwtTokenGenerator jwtTokenGenerator;
    private final ICustomerRegisterGateway gateway;
    private final ILoginAuthenticationPresenter presenter;

    // Dependency inject token generator
    public LoginService(IJwtTokenGenerator jwtTokenGenerator, ICustomerRegisterGateway gateway, ILoginAuthenticationPresenter presenter) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.gateway = gateway;
        this.presenter = presenter;
    }

    // Can be change to RegisterRequestModel
    // Throw exception of AuthenticationResult
    public LoginResponseModel login(String email, String password) throws AuthenticationCustomException {

        if (!gateway.existsByEmail()) {
            return presenter.prepareFailView(new WrongEmailException("Wrong email entered"));
        }

        // Incorrect email

        LoginResponseModel responseModel = new LoginResponseModel(randomUUID(), "firstName", "lastName", email, "token");
        return presenter.prepareSuccessView(responseModel);
    }
}
