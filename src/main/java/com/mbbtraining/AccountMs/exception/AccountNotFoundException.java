package com.mbbtraining.AccountMs.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(){}

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message);
    }
}
