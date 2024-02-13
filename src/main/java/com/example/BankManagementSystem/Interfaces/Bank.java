package com.example.BankManagementSystem.Interfaces;

import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;

import java.util.List;

public interface Bank {

    // Create
    public Customer add(CustomerDto customerDto);
    public Employee add(EmployeeDto employee);
    public Account add(AccountDto accountDto);
    public Loan add(LoanDto loanDto);
    public Branch add(BranchDto branchDto);

    public Transaction add(TransactionDto transaction);

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