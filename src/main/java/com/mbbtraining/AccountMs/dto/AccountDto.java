package com.mbbtraining.AccountMs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private int id;
    private long accountId;
    private String name;
    private int casaAccount;
    private String accountType;
    private String status;
}
