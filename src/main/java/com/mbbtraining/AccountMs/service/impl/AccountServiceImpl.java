package com.mbbtraining.AccountMs.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbbtraining.AccountMs.dto.AccountDto;
import com.mbbtraining.AccountMs.entity.Account;
import com.mbbtraining.AccountMs.repository.AccountRepository;
import com.mbbtraining.AccountMs.response.ExternalResponse;
import com.mbbtraining.AccountMs.response.ResponseHandler;
import com.mbbtraining.AccountMs.service.AccountService;
import com.mbbtraining.AccountMs.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    ResponseHandler<AccountDto>  responseHandler= new ResponseHandler<>();

    private final ModelMapper modelMapper;

    private final NotificationService notificationService;

    public RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper;


    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper, NotificationService notificationService, ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<ResponseHandler<List<AccountDto>>> retrieveAccount(){

        List<AccountDto> data = accountRepository.findAll()
                .stream()
                .map(value -> modelMapper.map(value, AccountDto.class)).toList();

        return ResponseEntity.ok(responseHandler.generateSuccessResponse(modelMapper));
    }

    @Override
    public ResponseEntity<ResponseHandler> createAccount(Account account) {

        String externalURL = "https://yesno.wtf/api";

        ResponseEntity<String> response = restTemplate.exchange(externalURL,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<String>() {
                });

        if(response.getStatusCode().is2xxSuccessful()){
            try {
                String responseBody = response.getBody();
                ExternalResponse externalResponse = objectMapper.readValue(responseBody, ExternalResponse.class);
                String answer = externalResponse.getAnswer();
                log.info(answer);

                if(answer != null){
                    if(answer.equals("yes"))
                    {
                        if(account.getName() == null){
                            return ResponseEntity.ok(responseHandler.generateFailResponse("Name must be filled"));
                        } else if (account.getCasaAccount() == 0){
                            return ResponseEntity.ok(responseHandler.generateFailResponse("Casa account must be filled"));
                        } else if (account.getAccountType() == null) {
                            return ResponseEntity.ok(responseHandler.generateFailResponse("Account Tye must be filled"));
                        }
                        else {
                            LocalDate currentDate = LocalDate.now();
                            account.setCreatedDate(currentDate);
                            account.setModifiedDate(currentDate);
                            accountRepository.save(account);
                            return ResponseEntity.ok(responseHandler.generateSuccessResponse("Account Creation Success"));
                        }
                    } else if (answer.equals("no")) {
                        return ResponseEntity.ok(responseHandler.generateFailResponse("Account creation failed"));

                    }
                } return ResponseEntity.ok(responseHandler.generateFailResponse("Failed to do eligible checking"));
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(responseHandler.generateFailResponse("Failed to parse eligibility answer"));
            }
        } else {
            return ResponseEntity.ok(responseHandler.generateFailResponse("Failed to fetch eligibility checking"));
        }
    }

    @Override
    public ResponseEntity<ResponseHandler> updateAccount(int id, AccountDto accountDto) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account updateAccount = optionalAccount.get();

            if (accountDto.getAccountId() != 0) {
                updateAccount.setAccountId(accountDto.getAccountId());
            }
            if (accountDto.getName() != null) {
                updateAccount.setName(accountDto.getName());
            }
            if (accountDto.getCasaAccount() != 0) {
                updateAccount.setCasaAccount(accountDto.getCasaAccount());
            }
            if (accountDto.getAccountType() != null) {
                updateAccount.setAccountType(accountDto.getAccountType());
            }
            if (accountDto.getStatus() != null) {
                updateAccount.setStatus(accountDto.getStatus());
            }

            LocalDate localDate = LocalDate.now();
            updateAccount.setModifiedDate(localDate);
            accountRepository.save(updateAccount);

            return ResponseEntity.ok(responseHandler.generateSuccessResponse(updateAccount));
        } else {
            return ResponseEntity.ok(responseHandler.generateFailResponse("Account ID not found" + id));
        }
    }

    @Override
    public ResponseEntity<ResponseHandler> updateMultipleAccounts(List<AccountDto> accountDtoList) {

        for (AccountDto accountDto : accountDtoList) {
            Optional<Account> optionalAccount = accountRepository.findById(accountDto.getId());
            if (optionalAccount.isPresent()) {
                Account existingAccount = optionalAccount.get();
                existingAccount.setName(accountDto.getName());
                existingAccount.setAccountType(accountDto.getAccountType());

                accountRepository.save(existingAccount);

                notificationService.sendNotification("Multiple accounts updated successfully");
            } else {
                throw new RuntimeException("Account not found with ID: " + accountDto.getId());
            }
        }
        return ResponseEntity.ok(responseHandler.generateSuccessResponse(accountDtoList));
    }

    @Override
    public ResponseEntity<?> deleteAccount(int id) {

        Optional<Account> optionalAccountDto = accountRepository.findById(id);
        if (optionalAccountDto.isPresent()){
            accountRepository.deleteById(id);
            return ResponseEntity.ok(responseHandler.generateSuccessResponse("Account " + id + "successfully deleted"));
        } else {

            return ResponseEntity.ok(responseHandler.generateFailResponse("Account " + id + "not found"));
        }
    }
}
