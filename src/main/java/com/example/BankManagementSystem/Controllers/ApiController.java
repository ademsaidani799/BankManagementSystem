package com.example.BankManagementSystem.Controllers;

import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;
import com.example.BankManagementSystem.Services.BankImplService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ApiController {
    private final BankImplService bankImplService;

    public ApiController(BankImplService bankImplService) {
        this.bankImplService = bankImplService;
    }

    //CustomerDto controller
    @PostMapping("api/teller/customer")
    public Customer createCustomer(@RequestBody CustomerDto customerDto) {
        return this.bankImplService.add(customerDto);
    }

    @DeleteMapping("api/teller/customer/{id}")
    public void removeCustomer(@PathVariable("id") Long id) {
        this.bankImplService.deleteCustomer(id);
    }

    ;

    @GetMapping("api/teller/customer/{id}")
    public Customer findCustomerByID(@PathVariable("id") Long id) {
        return this.bankImplService.findCustomer(id);
    }

    @GetMapping("api/teller/customer")
    public List<Customer> findAllCustomers() {
        return this.bankImplService.findAllCustomers();
    }


    //Employee controller
    @PostMapping("api/Admin/teller")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto) {
        return this.bankImplService.add(employeeDto);
    }

    @PutMapping("api/Admin/teller/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {

        return this.bankImplService.update(id, employeeDto);
    }

    @DeleteMapping("api/Admin/teller/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {
        this.bankImplService.deleteEmployee(id);
    }

    //  public boolean setManager(){};

    // AccountDto controller
    @PostMapping("api/teller/account")
    public Account createAccount(@RequestBody AccountDto accountDto) {
        return this.bankImplService.add(accountDto);
    }

    @PutMapping("api/teller/account/{id}")
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        return this.bankImplService.update(id, accountDto);
    }

    ;

    @DeleteMapping("api/teller/account/{id}")
    public void removeAccount(@PathVariable("id") Long id) {
        this.bankImplService.deleteAccount(id);
    }

    @GetMapping("api/teller/account/{id}")
    public List<Account> getAccountsOfCustomer(@PathVariable("id") Long id) {
        return this.bankImplService.findAccountsOfCustomer(id);
    }

    @GetMapping("api/teller/account/details/{id}")

    public String getAccountDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getAccountDetails(id);
    }

    ;

    //LoanDto contoller
    @PostMapping("api/teller/loan")
    public Loan createLoan(@RequestBody LoanDto loanDto) {
        return this.bankImplService.addLoan(loanDto);

    }

    @PutMapping("api/teller/loan/{id}")
    public Loan updateLoan(@PathVariable("id") Long id, @RequestBody LoanDto loanDto) {
        return this.bankImplService.update(id, loanDto);
    }

    @DeleteMapping("api/teller/loan/{id}")
    public void removeLoan(@PathVariable("id") Long id) {
        this.bankImplService.deleteLoan(id);
    }

    @GetMapping("api/teller/loan/{id}")
    public List<Loan> getLoansOfCustomer(@PathVariable("id") Long id) {
        return this.bankImplService.findLoansOfCustomer(id);
    }

    @GetMapping("api/teller/loan/details/{id}")
    public String getLoanDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getLoanDetails(id);
    }

    //BranchDto contoller.
    @PostMapping("api/admin/branch")
    public Branch createBranch(@RequestBody BranchDto branchDto) {
        return this.bankImplService.addBranch(branchDto);
    }

    @PutMapping("api/admin/branch/{id}")
    public Branch updateBranch(@PathVariable("id") Long id, @RequestBody BranchDto branchDto) {
        return this.bankImplService.update(id, branchDto);
    }

    @DeleteMapping("api/admin/branch/{id}")
    public void removeBranch(@PathVariable("id") Long id) {
        this.bankImplService.deleteBranch(id);
    }

    @GetMapping("api/admin/branch/details/{id}")
    public String getBranchDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getBranchDetails(id);
    }

    @GetMapping("api/admin/branch/employees/{branchId}")
    public List<Employee> getBranchEmployees(@PathVariable("branchId") Long branchId) {
        return this.bankImplService.getBranchEmployees(branchId);
    }

    //Transaction controller
    @GetMapping("api/teller/transaction/account/{accountId}")
    public List<Transaction> getTransactionsOfAccount(@PathVariable("accountId") Long accountId) {
        return this.bankImplService.findTransactionsOfAccount(accountId);
    }

    @GetMapping("api/teller/transaction/{transactionId}")
    public String getTransactionDetails(@PathVariable("transactionId") Long transactionId) {
        return this.bankImplService.getTransactionDetails(transactionId);
    }

    @PostMapping("api/customer/{accountId}")
    public String withdraw(@PathVariable("accountId") Long accountId, @RequestBody BigDecimal amount) {
        return this.bankImplService.withdraw(accountId, amount);
    }
//when security is setup we can add a parameter Authentication auth
    //and get the username by auth.getName() instead of accountId parameter

    @PostMapping("api/teller/account/deposit/{accountId}")
    public String deposit(@PathVariable("accountId") Long accountId, @RequestBody BigDecimal amount) {
        return this.bankImplService.deposit(accountId, amount);

    }

    @PostMapping("api/customer/transfer/{fromAccountId}/{toAccountId}")
    public String transfer(@PathVariable("fromAccountId") Long fromAccountId, @PathVariable("toAccountId") Long toAccountId, @RequestBody BigDecimal amount) {
        return this.bankImplService.transfer(fromAccountId, toAccountId, amount);
    }
}