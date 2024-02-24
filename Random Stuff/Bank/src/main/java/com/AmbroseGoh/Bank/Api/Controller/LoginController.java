package com.AmbroseGoh.Bank.Api.Controller;

import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Application.Services.Authentication.ILoginService;
import com.AmbroseGoh.Bank.Contracts.LoginResponseModel;
import com.AmbroseGoh.Bank.Contracts.RegisterRequest;

public class LoginController {

    private final ILoginService authenticationService;

    // Dependency Inject AuthService
    public LoginController(ILoginService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public LoginResponseModel login(RegisterRequest request) throws AuthenticationCustomException {
        return authenticationService.login(
                request.getEmail(),
                request.getPassword()
        );

    }
}
