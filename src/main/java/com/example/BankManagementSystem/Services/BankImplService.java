package com.example.BankManagementSystem.Services;


import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;
import com.example.BankManagementSystem.Interfaces.Bank;
import com.example.BankManagementSystem.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BankImplService implements Bank {

    private final AccountRepositories accountRepositories;
    private final BranchRepositories branchRepositories;
    private final CustomerRepositories customerRepositories;
    private final EmployeeRepositories employeeRepositories;
    private final LoanRepositories loanRepositories;
    private final TransactionRepositories transactionRepositories;

    public BankImplService(AccountRepositories accountRepositories, BranchRepositories branchRepositories, CustomerRepositories customerRepositories, EmployeeRepositories employeeRepositories, LoanRepositories loanRepositories, TransactionRepositories transactionRepositories) {
        this.accountRepositories = accountRepositories;
        this.branchRepositories = branchRepositories;
        this.customerRepositories = customerRepositories;
        this.employeeRepositories = employeeRepositories;
        this.loanRepositories = loanRepositories;
        this.transactionRepositories = transactionRepositories;
    }

    // Implementation of Bank methods

    //Implementation of Create
public Customer add(CustomerDto customerDto){
    Customer customer = new Customer();
    customer.setLogin(customerDto.getLogin());
    customer.setPasshash(customerDto.getPasshash());
    customer.setName(customerDto.getName());
    customer.setPhone(customerDto.getPhone());
    customer.setEmail(customerDto.getEmail());
    customer.setRegistrationDate(new Date());
    return customerRepositories.saveAndFlush(customer);

}

    public Employee add(EmployeeDto employeeDto) {
        // Fetch Branch
        Branch branchTemp = branchRepositories.findByAddress(employeeDto.getBranch().getAddress());

        // Fetch Manager if provided
        Employee manager = null;
        if (employeeDto.getManager().getLogin() != null) {
            manager = employeeRepositories.findByLogin(employeeDto.getManager().getLogin());
        }

        // Create new Employee
        Employee employee = new Employee();
        employee.setLogin(employeeDto.getLogin());
        employee.setPasshash(employeeDto.getPasshash());
        employee.setName(employeeDto.getName());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setRegistrationDate(new Date());

        // Set Branch and Manager for the new Employee
        employee.setBranch(branchTemp);
        employee.setManager(manager);

        // Save and return the new Employee
        return employeeRepositories.saveAndFlush(employee);
    }

    public Account add(AccountDto accountDto) {

        Customer customerTemp = customerRepositories.findByLogin(accountDto.getCustomer().getLogin());
        Branch branchTemp = branchRepositories.findByAddress(accountDto.getBranch().getAddress());

        // Check if the customer already has a list of accounts
        List<Account> accounts = customerTemp.getAccounts();
        if (accounts == null) {
            accounts = new ArrayList<>();
        }

        Account account = new Account();
        account.setCustomer(customerTemp);
        account.setBranch(branchTemp);
        account.setOpeningDate(new Date());
        account.setType(accountDto.getType());

        // Add the new account to the existing list of accounts
        accounts.add(account);

        // Update the customer's accounts list
        customerTemp.setAccounts(accounts);
        customerRepositories.saveAndFlush(customerTemp);
        return accountRepositories.saveAndFlush(account);
    }

    public Loan addLoan(LoanDto loanDto) {

        Customer customerTemp = customerRepositories.findByLogin(loanDto.getCustomerDto().getLogin());
        Branch branchTemp = branchRepositories.findByAddress(loanDto.getBranchDto().getAddress());

        // Check if the customer already has a list of loans
        List<Loan> loans = customerTemp.getLoans();
        if (loans == null) {
            loans = new ArrayList<>();
        }

        Loan loan = new Loan();
        loan.setCustomer(customerTemp);
        loan.setBranch(branchTemp);
        loan.setStartingDate(new Date());
        loan.setType(loanDto.getType());
        loan.setDueDate(loanDto.getDueDate());
        loan.setAmount(loanDto.getAmount());

        // Add the new loan to the existing list of loans
        loans.add(loan);

        // Update the customer's loans list
        customerTemp.setLoans(loans);

        customerRepositories.saveAndFlush(customerTemp);
        return loanRepositories.saveAndFlush(loan);
    }

    public Branch addBranch(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setAddress(branchDto.getAddress());
        branch.setPhone(branchDto.getPhone());

        return branchRepositories.saveAndFlush(branch);
    }
    public Transaction addTransaction(TransactionDto transactionDto) {
        Account accountTemp = accountRepositories.findById(transactionDto.getAccountId()).orElse(null);
        Employee tellerTemp = employeeRepositories.findById(transactionDto.getTellerId()).orElse(null);

        if (accountTemp == null || tellerTemp == null) {
            // Handle the case where the account or teller is not found
            // You might throw an exception or handle it based on your application logic
            // For example:
            throw new EntityNotFoundException("Account or teller not found");
        }

        Transaction transaction = new Transaction();
        transaction.setDate(new Date());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setAccount(accountTemp);
        transaction.setTeller(tellerTemp);
        transaction.setType(transactionDto.getType());

        return transactionRepositories.saveAndFlush(transaction);
    }

}