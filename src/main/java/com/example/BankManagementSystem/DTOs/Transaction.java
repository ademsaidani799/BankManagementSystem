package com.example.BankManagementSystem.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

    private int id;
    private Date date;

    private BigDecimal amount;

    private Account account;

    private Employee teller;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Employee getTeller() {
        return teller;
    }

    public void setTeller(Employee teller) {
        this.teller = teller;
    }
}
