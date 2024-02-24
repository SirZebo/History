package com.AmbroseGoh.Bank.Api.Controller;

import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Application.Services.Authentication.IRegisterService;
import com.AmbroseGoh.Bank.Contracts.RegisterRequest;
import com.AmbroseGoh.Bank.Contracts.RegisterResponseModel;

public class RegisterController {

    private final IRegisterService authenticationService;

    // Dependency Inject AuthService
    public RegisterController(IRegisterService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public RegisterResponseModel register(RegisterRequest request) throws AuthenticationCustomException {
        return authenticationService.register(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
    }

}
