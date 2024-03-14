package com.mbbtraining.AccountMs.service;

import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.response.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    ResponseEntity<ResponseHandler<List<AccountDto>>> retrieveAccount();

    ResponseEntity<ResponseHandler> createAccount(Account account);
//
    ResponseEntity<ResponseHandler> updateAccount(int id, AccountDto accountDto);
//
    ResponseEntity<ResponseHandler>  updateMultipleAccounts(List<AccountDto> accountDtoList);
//
    ResponseEntity<?> deleteAccount(int id);

}