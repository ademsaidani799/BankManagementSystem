package com.example.BankManagementSystem.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Customer extends Person {

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
    @OneToMany(mappedBy = "customer")
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
