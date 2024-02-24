package com.AmbroseGoh.Bank.Application.Presenter.Authentication;


import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Contracts.LoginResponseModel;

public interface ILoginAuthenticationPresenter {
    LoginResponseModel prepareFailView(AuthenticationCustomException e) throws AuthenticationCustomException;

    LoginResponseModel prepareSuccessView(LoginResponseModel responseModel);
}
