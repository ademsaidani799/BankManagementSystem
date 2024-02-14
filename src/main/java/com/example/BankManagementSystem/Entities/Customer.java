package com.example.BankManagementSystem.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Set;

@Entity
public class Customer extends Person {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Account> accounts;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Loan> loans;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
