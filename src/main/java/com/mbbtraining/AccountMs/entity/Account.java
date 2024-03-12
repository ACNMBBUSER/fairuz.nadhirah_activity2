package com.mbbtraining.AccountMs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "Account", schema = "accountdb")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "AccountId")
    private long accountId;

    @Column(name = "Name")
    private String name;

    @Column(name = "casaaccount")
    private int casaAccount;

    @Column(name = "AccountType")
    private String accountType;

    @Column(name = "Status")
    private String status;

    @Column(name = "TransactStatus")
    private String transactStatus;

    @Column(name = "TransactionLimit")
    private float transactionLimit;

    @Column(name = "CreatedDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @Column(name = "ModifiedDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modifiedDate;

    public Account() {
    }

    public Account(int id, long accountId, String name, int casaAccount, String accountType, String status, String transactStatus, float transactionLimit, LocalDate createdDate, LocalDate modifiedDate) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.casaAccount = casaAccount;
        this.accountType = accountType;
        this.status = status;
        this.transactStatus = transactStatus;
        this.transactionLimit = transactionLimit;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public long getAccountId() {
//        return accountId;
//    }
//
//    public void setAccountId(long accountId) {
//        this.accountId = accountId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getCasaAccount() {
//        return casaAccount;
//    }
//
//    public void setCasaAccount(int casaAccount) {
//        this.casaAccount = casaAccount;
//    }
//
//    public String getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(String accountType) {
//        this.accountType = accountType;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getTransactStatus() {
//        return transactStatus;
//    }
//
//    public void setTransactStatus(String transactStatus) {
//        this.transactStatus = transactStatus;
//    }
//
//    public float getTransactionLimit() {
//        return transactionLimit;
//    }
//
//    public void setTransactionLimit(float transactionLimit) {
//        this.transactionLimit = transactionLimit;
//    }
//
//    public LocalDate getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDate createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public LocalDate getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(LocalDate modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }
}

