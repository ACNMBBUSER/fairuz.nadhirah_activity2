package com.mbbtraining.AccountMs.service;

import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    public String createAccount(Account account);

    public List<AccountDto> getAllAccount();

    public String deleteAccount(int id);

    public Object getAccount(int id);

    public Account updateById(int id, Account updateAccount);

    public void updateMultipleAccounts(List<AccountDto> accountDtoList);

    public ResponseEntity<String> createAccounts();
}