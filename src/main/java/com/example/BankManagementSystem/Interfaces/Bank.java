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
    public Loan addLoan(LoanDto loanDto);
    public Branch addBranch(BranchDto branchDto);

    public Transaction addTransaction(TransactionDto transactionDto);

    // Read
   public List<Customer> findAllCustomers();
   public Customer findCustomer(long param);

   public List<Employee>findAllEmployees();
    public Employee findEmployee(Long id);

    public List<Account>findAccountsOfCustomer(Long id);
    public List<Loan>findLoansOfCustomer(Long id);

    public List<Transaction> findTransactionsOfAccount(Long id);
    public List<Transaction> findTransactionsOfCustomer(Long id);
    public String getTransactionDetails(Long id);

    // Update
    public Customer update(Long customerId, CustomerDto customerDto);
    public Employee update(Long employeeId, EmployeeDto employeeDto);
    public Account update(Long accountId, AccountDto accountDto);
    public Loan update(Long loanId, LoanDto loanDto);

    public Branch update(Long branchId, BranchDto branchDto);

    // Delete
    public void deleteCustomer(Long id);
    public void deleteEmployee(Long id);
    public void deleteAccount(Long id);
    public void deleteLoan(Long id);
    public void deleteBranch(Long id);
}