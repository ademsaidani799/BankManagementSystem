package com.example.BankManagementSystem.Controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;
import com.example.BankManagementSystem.Serializer.ReaquestAmount;
import com.example.BankManagementSystem.Services.BankImplService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@RestController
public class ApiController {
    private final BankImplService bankImplService;

    public ApiController(BankImplService bankImplService) {
        this.bankImplService = bankImplService;
    }

    //CustomerDto controller
    //9
    @PostMapping("api/teller/customer")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public Customer createCustomer(@RequestBody CustomerDto customerDto) throws Exception {
        return this.bankImplService.add(customerDto);
    }

    //11
    @PutMapping("api/teller/customer/{id}")
    public Customer updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable("id") Long id) {
        return this.bankImplService.update(id, customerDto);
    }

    //12
    @DeleteMapping("api/teller/customer/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public void removeCustomer(@PathVariable("id") Long id) {
        this.bankImplService.deleteCustomer(id);
    }

    //10
    @GetMapping("api/teller/customer/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public Customer findCustomerByID(@PathVariable("id") Long id) {
        return this.bankImplService.findCustomer(id);
    }

    //13
    @GetMapping("api/teller/customer")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public List<Customer> findAllCustomers() {
        return this.bankImplService.findAllCustomers();
    }


    //Employee controller
    // admin is an employee
    //5
    @PostMapping("api/Admin/teller")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto) {
        return this.bankImplService.add(employeeDto);
    }

    //8
    @PutMapping("api/Admin/teller/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDto employeeDto) {

        return this.bankImplService.update(id, employeeDto);
    }

    //6
    @GetMapping("api/Admin/teller/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee findEmployee(@PathVariable("id") Long id) {
        return this.bankImplService.findEmployee(id);
    }

    //7
    @DeleteMapping("api/Admin/teller/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeEmployee(@PathVariable("id") Long id) {
        this.bankImplService.deleteEmployee(id);
    }

    //  public boolean setManager(){};

    // AccountDto controller
    //14
    @PostMapping("api/teller/account")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public Account createAccount(@RequestBody AccountDto accountDto) throws Exception {
        return this.bankImplService.add(accountDto);

    }

    //17
    @PutMapping("api/teller/account/{id}")
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {
        return this.bankImplService.update(id, accountDto);
    }

    //16
    @DeleteMapping("api/teller/account/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public void removeAccount(@PathVariable("id") Long id) {
        this.bankImplService.deleteAccount(id);
    }

    //15
    @GetMapping("api/teller/account/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public List<Account> getAccountsOfCustomer(@PathVariable("id") Long id) {
        return this.bankImplService.findAccountsOfCustomer(id);
    }

    //18
    @GetMapping("api/teller/account/details/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public String getAccountDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getAccountDetails(id);
    }

    //LoanDto contoller
    //19
    @PostMapping("api/teller/loan")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public Loan createLoan(@RequestBody LoanDto loanDto) {
        return this.bankImplService.addLoan(loanDto);

    }
    //22

    @PutMapping("api/teller/loan/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public Loan updateLoan(@PathVariable("id") Long id, @RequestBody LoanDto loanDto) {
        return this.bankImplService.update(id, loanDto);
    }

    //21
    @DeleteMapping("api/teller/loan/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public void removeLoan(@PathVariable("id") Long id) {
        this.bankImplService.deleteLoan(id);
    }

    //20
    @GetMapping("api/teller/loan/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public List<Loan> getLoansOfCustomer(@PathVariable("id") Long id) {
        return this.bankImplService.findLoansOfCustomer(id);
    }

    //23
    @GetMapping("api/teller/loan/details/{id}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public String getLoanDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getLoanDetails(id);
    }

    //BranchDto contoller.

//1

    @PostMapping("api/admin/branch")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Branch createBranch(@RequestBody BranchDto branchDto) {
        return this.bankImplService.addBranch(branchDto);
    }

    //4
    @PutMapping("api/admin/branch/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Branch updateBranch(@PathVariable("id") Long id, @RequestBody BranchDto branchDto) {
        return this.bankImplService.update(id, branchDto);
    }

    //3
    @DeleteMapping("api/admin/branch/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeBranch(@PathVariable("id") Long id) {
        this.bankImplService.deleteBranch(id);
    }

    //2
    @GetMapping("api/admin/branch/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getBranchDetails(@PathVariable("id") Long id) {
        return this.bankImplService.getBranchDetails(id);
    }


    @GetMapping("api/admin/branch/employees/{branchId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Employee> getBranchEmployees(@PathVariable("branchId") Long branchId) {
        return this.bankImplService.getBranchEmployees(branchId);
    }

    //Transaction controller
    //24
    @GetMapping("api/teller/transaction/account/{accountId}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public List<Transaction> getTransactionsOfAccount(@PathVariable("accountId") Long accountId) {
        return this.bankImplService.findTransactionsOfAccount(accountId);
    }

    ///25
    @GetMapping("api/teller/transaction/{transactionId}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public String getTransactionDetails(@PathVariable("transactionId") Long transactionId) {
        return this.bankImplService.getTransactionDetails(transactionId);
    }

    //26
    @PostMapping("api/customer/{accountId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String withdraw(@PathVariable("accountId") Long accountId, Authentication authentication, @RequestBody ReaquestAmount reaquestAmount) {

        return this.bankImplService.withdraw(accountId, authentication, reaquestAmount.getAmount(), true);
    }

    //when security is setup we can add a parameter Authentication auth
    //and get the username by auth.getName() instead of accountId parameter
//27
    @PostMapping("api/teller/account/deposit/{accountId}")
    @PreAuthorize("hasRole('ROLE_TELLER')")
    public String deposit(@PathVariable("accountId") Long accountId, Authentication authentication, @RequestBody ReaquestAmount reaquestAmount) {
        return this.bankImplService.deposit(accountId, authentication, reaquestAmount.getAmount());

    }

    //28
    @PostMapping("api/customer/transfer/{fromAccountId}/{toAccountId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String transfer(Authentication authentication, @PathVariable("fromAccountId") Long fromAccountId, @PathVariable("toAccountId") Long toAccountId, @RequestBody ReaquestAmount reaquestAmount) {
        return this.bankImplService.transfer(authentication, fromAccountId, toAccountId, reaquestAmount.getAmount());
    }
}