package com.mbbtraining.AccountMs.dto;

import lombok.Data;
import org.hibernate.annotations.SecondaryRow;

import java.awt.*;

@Data
public class AccountDto {

    private int id;
    private long accountId;
    private String name;
    private int casaAccount;
    private String accountType;
    private String status;
}
