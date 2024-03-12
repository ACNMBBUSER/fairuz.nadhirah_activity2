package com.mbbtraining.AccountMs.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.exception.AccountNotFoundException;
import com.mbbtraining.AccountMs.repository.AccountRepository;
import com.mbbtraining.AccountMs.service.AccountService;
import com.mbbtraining.AccountMs.service.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;

    private final NotificationService notificationService;

    String createUrl = "https://yesno.wtf/api";

    private final Environment environment;

    public RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper;


    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, NotificationService notificationService, Environment environment, ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.notificationService = notificationService;
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    public String createAccount(Account account){
        accountRepository.save(account);
        return "Success";
    }

    @Override
    public ResponseEntity<String> createAccounts(){
        ResponseEntity<String> response = restTemplate.exchange(createUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });
        return ResponseEntity.ok(response.getBody());

    }

//    @Override
//    public CreateDto createAccounts(Account account) {
//        return null;
//    }

//    @Override
//    public List<Account> getAllAccount(){
//        return accountRepository.findAll();

//    }

    @Override
    public List<AccountDto> getAllAccount(){
        return accountRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private AccountDto convertEntityToDto(Account account){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        AccountDto accountDto = new AccountDto();
        accountDto = modelMapper.map(account, AccountDto.class);
        return accountDto;
    }

    @Override
    public Account getAccount(int id){
        if(accountRepository.findById(id).isEmpty())
            throw new AccountNotFoundException("Request Account Details does not exist");
        return accountRepository.findById(id).get();
    }
//    @Override
//    public String updateAccount(int id){
//        accountRepository.save(id);
//        return "Success";

//    }

    public Account updateById(int id, Account updateAccount){
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if(optionalAccount.isPresent()){
            Account existingAccount = optionalAccount.get();
            existingAccount.setName(updateAccount.getName());
            existingAccount.setAccountType(updateAccount.getAccountType());

            return accountRepository.save(existingAccount);
        } else {
            throw new EntityNotFoundException("Account with ID" + id + " not found");
        }
    }

    public void updateMultipleAccounts(List<AccountDto> accountDtoList){
        for (AccountDto accountDto : accountDtoList ){
            Optional<Account> optionalAccount = accountRepository.findById(accountDto.getId());
            if (optionalAccount.isPresent()){
                Account existingAccount = optionalAccount.get();
                existingAccount.setName(accountDto.getName());
                existingAccount.setAccountType(accountDto.getAccountType());

                accountRepository.save(existingAccount);

                notificationService.sendNotification("Multiple accounts updated successfully");
            } else {
                throw new RuntimeException("Account not found with ID: " + accountDto.getId());
            }
        }
    }

    @Override
    public String deleteAccount(int id){
        accountRepository.deleteById(id);
        return "Success";
    }
}
