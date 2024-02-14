package com.example.BankManagementSystem.DTOs;

import java.util.List;
import java.util.Set;

public class CustomerDto extends PersonDto {
    private Long id;

    public List<AccountDto> getAccountDtos() {
        return accountDtos;
    }

    public void setAccountDtos(List<AccountDto> accountDtos) {
        this.accountDtos = accountDtos;
    }

    public List<LoanDto> getLoanDtos() {
        return loanDtos;
    }

    public void setLoanDtos(List<LoanDto> loanDtos) {
        this.loanDtos = loanDtos;
    }

    public Long getId() {
        return id;
    }

    private List<AccountDto> accountDtos;

    private List<LoanDto> loanDtos;

    public List<AccountDto> getAccounts() {
        return accountDtos;
    }

    public void setAccounts(List<AccountDto> accountDtos) {
        this.accountDtos = accountDtos;
    }

    public List<LoanDto> getLoans() {
        return loanDtos;
    }

    public void setLoans(List<LoanDto> loanDtos) {
        this.loanDtos = loanDtos;
    }
}
