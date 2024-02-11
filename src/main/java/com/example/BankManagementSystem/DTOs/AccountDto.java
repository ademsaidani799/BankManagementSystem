package com.example.BankManagementSystem.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDto {

    private int id;

    private CustomerDto customerDto;

    private BranchDto branchDto;

    private Date openingDate;

    private BigDecimal currentBalance;

    private BigDecimal interestRate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customerDto;
    }

    public void setCustomer(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public BranchDto getBranch() {
        return branchDto;
    }

    public void setBranch(BranchDto branchDto) {
        this.branchDto = branchDto;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}