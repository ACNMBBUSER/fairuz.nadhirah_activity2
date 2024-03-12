package com.mbbtraining.AccountMs.controller;


import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.dto.CreateDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.repository.AccountRepository;
import com.mbbtraining.AccountMs.response.ResponseHandler;
import com.mbbtraining.AccountMs.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    public AccountController(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/get")
    public List<AccountDto> getAllAccountDetails()
    {
        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccountDetails(@PathVariable("id") int id){
        return ResponseHandler.responseBuilder("Account details are given here",
                HttpStatus.OK, accountService.getAccount(id));
    }

// in use
    @PostMapping("/create")
    public String createAccount(@RequestBody Account account)
    {
        accountService.createAccount(account);
        return "Account created successfully";
    }

//    @PostMapping("/creates")
//    public ResponseEntity<ResponseHandler<CreateDto>> createAccounts(@RequestBody )

//    @PostMapping("/create")
//    public ResponseEntity<String> createAccounts(@RequestBody Account account)
//    {
////        accountService.createAccount(account);
//        return accountService.createAccounts();
//    }


//    @PostMapping("/create")
//    public ResponseEntity<AccountResponse> createAccount(@RequestBody Account account, AccountRequest request){
//
//        accountService.createAccount(account);
//        AccountResponse response = new AccountResponse("Accounts updated successfully");
//        return ResponseEntity.ok(response);
//    }

//    @PutMapping("/update")
//    public String updateAccount(@RequestBody Account account)
//    {
//        accountService.updateAccount(account);
//        return "Account updated successfully";
//    }

//    @PutMapping("/update/{id}")
//    public String updateAccount(@PathVariable("id") int id @RequestBody )
//    {
//        accountService.updateAccount(id);
//        return "Account updated successfully";
//    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccountById(@PathVariable int id, @RequestBody Account updateAccount){
        Account accountUpdate = accountService.updateById(id, updateAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }

    @PutMapping("/updateMultiple")
    public ResponseEntity<String> updateMultipleAccounts(@RequestBody List<AccountDto> accountDtoList){
        accountService.updateMultipleAccounts(accountDtoList);
        return ResponseEntity.ok("Accounts update successfully");
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") int id)
    {
        accountService.deleteAccount(id);
        return "Account has been deleted";
    }
}
