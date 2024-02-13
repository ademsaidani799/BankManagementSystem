package com.example.BankManagementSystem.Interfaces;

import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;

import java.util.List;
import java.util.Optional;

public interface Bank {

    // Create
    public Customer add(CustomerDto customerDto);
    public Employee add(EmployeeDto employee);
    public Account add(AccountDto accountDto);
    public Loan add(LoanDto loanDto);
    public Branch add(BranchDto branchDto);

    public Transaction add(TransactionDto transaction);

    // Read
   public List<Customer> findAllCustomers();
   public Optional<Customer> findCustomer(long param);

   public List<Employee>findAllEmployees();
    public Optional<Employee> findEmployee(Long id);

    public List<Account>findAccountsOfCustomer(Long id);
    public List<Loan>findLoansOfCustomer(Long id);

    public List<Transaction> findTransactionsOfAccount(Long id);
    public List<Transaction> findTransactionsOfCustomer(Long id);
    public String getTransactionDetails(Long id);

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