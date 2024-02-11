package com.example.BankManagementSystem.Interfaces;

import com.example.BankManagementSystem.DTOs.*;

import java.util.List;

public interface Bank {

    // Create
    boolean add(CustomerDto customerDto);
    boolean add(EmployeeDto employee);
    boolean add(AccountDto accountDto);
    boolean add(LoanDto loanDto);
    boolean add(BranchDto branchDto);

    boolean add(TransactionDto transaction);

    // Read
    List<CustomerDto> findAllCustomers();
    CustomerDto findCustomer(String param);

    List<EmployeeDto>findAllEmployees();
    EmployeeDto findEmployee(String param);

    List<AccountDto>findAccountsOfCustomer();
    List<LoanDto>findLoansOfCustomer();

    List<TransactionDto> findTransactionsOfAccount();
    List<TransactionDto> findTransactionsOfCustomer();
    String getTransactionDetails();

    // Update
    boolean update(CustomerDto customerDto);
    boolean update(EmployeeDto employeeDto);
    boolean update(AccountDto accountDto);
    boolean update(LoanDto loanDto);
    boolean update(BranchDto branchDto);

    // Delete
    boolean delete(CustomerDto customerDto);
    boolean delete(EmployeeDto employeeDto);
    boolean delete(AccountDto accountDto);
    boolean delete(LoanDto loanDto);
    boolean delete(BranchDto branchDto);
}