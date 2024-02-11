package com.example.BankManagementSystem.Controllers;

import com.example.BankManagementSystem.DTOs.*;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ApiController {
    //CustomerDto controller
    public CustomerDto createCustomer() {};

    public boolean removeCustomer(){};

    public CustomerDto getCustomerOfAccount(AccountDto accountDto){};

    public CustomerDto findCustomerByID(int id) {};

    public List<CustomerDto> findCustomersByName(String name) {};


    //Employee controller
    public EmployeeDto createEmployee(){};

    public boolean updateEmployee(){};

    public boolean removeEmployee(){};

    public boolean setManager(){};

    // AccountDto controller
    public AccountDto createAccount(){};

    public boolean updateAccount(){};

    public boolean removeAccount(){};

    public List<AccountDto> getAccountsOfCustomer(){};

    public String getAccountDetails(){};

    //LoanDto contoller
    public LoanDto createLoan(){};

    public boolean updateLoan(){};

    public boolean removeLoan(){};

    public List<LoanDto> getLoansOfCustomer(){};

    public String getLoanDetails(){};

    //Transaction controller
    public List<TransactionDto> getTransactionsOfAccount(AccountDto accountDto){};

    public String getTransactionDetails(TransactionDto transactionDto){};


    public boolean withdraw(AccountDto accountDto, BigDecimal amount)  {};


    public boolean deposit(AccountDto accountDto, BigDecimal amount) {};

     public boolean transfer(AccountDto from, AccountDto to, BigDecimal amount)  {};

    //BranchDto contoller.
    public BranchDto createBranch(){};

    public boolean updateBranch(){};

    public boolean removeBranch(){};

    public String getBranchDetails(){};

    public List<EmployeeDto> getBranchEmployees(){};
}
