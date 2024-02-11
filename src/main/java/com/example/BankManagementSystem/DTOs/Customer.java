package com.example.BankManagementSystem.DTOs;

import java.util.Set;

public class Customer extends Person{

    private Set<Account> accounts;

    private Set<Loan> loans;

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
