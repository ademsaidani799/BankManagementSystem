package com.example.BankManagementSystem.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {


    private Date date;

    private BigDecimal amount;
    private AccountDto accountDto;

    private EmployeeDto teller;
    private String type;



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountDto getAccount() {
        return accountDto;
    }

    public void setAccount(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public EmployeeDto getTeller() {
        return teller;
    }

    public void setTeller(EmployeeDto teller) {
        this.teller = teller;
    }
}
