package com.mbbtraining.AccountMs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class AccountNotFoundExceptionHandler {

    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<Object> handleAccountNotFoundException
            (AccountNotFoundException accountNotFoundException)
    {
        AccountException accountException = new AccountException(
                accountNotFoundException.getMessage(),
                accountNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(accountException, HttpStatus.NOT_FOUND);
    }
}
