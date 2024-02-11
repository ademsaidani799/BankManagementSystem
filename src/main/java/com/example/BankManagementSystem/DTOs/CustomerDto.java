package com.example.BankManagementSystem.DTOs;

import java.util.Set;

public class CustomerDto extends PersonDto {

    private Set<AccountDto> accountDtos;

    private Set<LoanDto> loanDtos;

    public Set<AccountDto> getAccounts() {
        return accountDtos;
    }

    public void setAccounts(Set<AccountDto> accountDtos) {
        this.accountDtos = accountDtos;
    }

    public Set<LoanDto> getLoans() {
        return loanDtos;
    }

    public void setLoans(Set<LoanDto> loanDtos) {
        this.loanDtos = loanDtos;
    }
}
