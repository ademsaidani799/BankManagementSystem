package com.example.BankManagementSystem.Services;


import com.example.BankManagementSystem.DTOs.AccountDto;
import com.example.BankManagementSystem.DTOs.CustomerDto;
import com.example.BankManagementSystem.DTOs.EmployeeDto;
import com.example.BankManagementSystem.Entities.*;
import com.example.BankManagementSystem.Interfaces.Bank;
import com.example.BankManagementSystem.Repositories.*;
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

    public Account add(AccountDto accountDto){


    }



}