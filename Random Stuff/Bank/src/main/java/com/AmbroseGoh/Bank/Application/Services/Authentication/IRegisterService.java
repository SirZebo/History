package com.AmbroseGoh.Bank.Application.Services.Authentication;

import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Contracts.RegisterResponseModel;

public interface IRegisterService {

    RegisterResponseModel register(String firstName, String lastName, String email, String password) throws AuthenticationCustomException;
}
