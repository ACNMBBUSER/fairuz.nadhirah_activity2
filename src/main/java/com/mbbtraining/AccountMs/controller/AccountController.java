package com.mbbtraining.AccountMs.controller;


import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.repository.AccountRepository;
import com.mbbtraining.AccountMs.response.ResponseHandler;
import com.mbbtraining.AccountMs.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    ResponseHandler<AccountDto> responseHandler = new ResponseHandler<>();

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

//    @GetMapping("/get")
//    public List<AccountDto> getAllAccountDetails()
//    {
//        return accountService.getAllAccount();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getAccountDetails(@PathVariable("id") int id){
//        return ResponseHandler.responseBuilder("Account details are given here",
//                HttpStatus.OK, accountService.getAccount(id));
//    }
//
//// in use
//    @PostMapping("/create")
//    public String createAccount(@RequestBody Account account)
//    {
//        accountService.createAccount(account);
//        return "Account created successfully";
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Account> updateAccountById(@PathVariable int id, @RequestBody Account updateAccount){
//        Account accountUpdate = accountService.updateById(id, updateAccount);
//        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
//    }
//
//    @PutMapping("/updateMultiple")
//    public ResponseEntity<String> updateMultipleAccounts(@RequestBody List<AccountDto> accountDtoList){
//        accountService.updateMultipleAccounts(accountDtoList);
//        return ResponseEntity.ok("Accounts update successfully");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteAccount(@PathVariable("id") int id)
//    {
//        accountService.deleteAccount(id);
//        return "Account has been deleted";
//    }

    @GetMapping("/retrieve")
    public ResponseEntity<ResponseHandler<List<AccountDto>>> getAll(){
        return accountService.retrieveAccount();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseHandler> createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseHandler> updateAccount(@PathVariable int id, @RequestBody AccountDto accountDto){
        return accountService.updateAccount(id, accountDto);
    }

    @PutMapping("/updateMultipleAccount")
    public ResponseEntity<String> updateMultipleAccount(@RequestBody List<AccountDto> accountDtoList){
        accountService.updateMultipleAccounts(accountDtoList);
        return ResponseEntity.ok("Multiple accounts has been updated");
    }

    @DeleteMapping("/delete/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id){
        return accountService.deleteAccount(id);
    }
}
