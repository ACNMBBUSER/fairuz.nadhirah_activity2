package com.mbbtraining.AccountMs.service;

import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.response.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

//    public String createAccount(Account account);
//
//    public List<AccountDto> getAllAccount();
//
//    public String deleteAccount(int id);
//
//    public Object getAccount(int id);
//
//    public Account updateById(int id, Account updateAccount);
//
//    public void updateMultipleAccounts(List<AccountDto> accountDtoList);
//
//    public ResponseEntity<String> createAccounts();

//    List<AccountDto> getAllAccount();

    ResponseEntity<ResponseHandler<List<AccountDto>>> retrieveAccount();

    ResponseEntity<ResponseHandler> createAccount(Account account);
//
    ResponseEntity<ResponseHandler> updateAccount(int id, AccountDto accountDto);
//
    ResponseEntity<ResponseHandler>  updateMultipleAccounts(List<AccountDto> accountDtoList);
//
    ResponseEntity<?> deleteAccount(int id);

}