package com.AmbroseGoh.Bank.Application.Presenter.Authentication;


import com.AmbroseGoh.Bank.Application.Exception.AuthenticationCustomException;
import com.AmbroseGoh.Bank.Contracts.RegisterResponseModel;

public interface IRegisterAuthenticationPresenter {
    RegisterResponseModel prepareFailView(AuthenticationCustomException e) throws AuthenticationCustomException;

    RegisterResponseModel prepareSuccessView(RegisterResponseModel responseModel);
}
