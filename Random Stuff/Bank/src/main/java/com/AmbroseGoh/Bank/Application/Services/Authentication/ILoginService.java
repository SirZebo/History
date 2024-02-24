package com.AmbroseGoh.Bank.Application.Services.Authentication;

import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Contracts.LoginResponseModel;

public interface ILoginService {

    LoginResponseModel login(String email, String password) throws AuthenticationCustomException;
}
