package com.example.BankManagementSystem.Controllers;

import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Customer;
import com.example.BankManagementSystem.Entities.Employee;
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
    public void removeCustomer(@PathVariable("id") Long id){
        this.bankImplService.deleteCustomer(id);
    };

    @GetMapping("api/teller/customer/{id}")
    public Customer findCustomerByID(@PathVariable("id") Long id) {
     return this.bankImplService.findCustomer(id);
    }
    @GetMapping("api/teller/customer")
    public List<Customer> findAllCustomers(){
        return this.bankImplService.findAllCustomers();
    }


    //Employee controller
    @PostMapping("api/Admin/teller")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto){
       return this.bankImplService.add(employeeDto);
    }

    @PutMapping("api/Admin/teller/{id}")
    public Employee updateEmployee(@PathVariable("id")Long id, @RequestBody EmployeeDto employeeDto) {

        return this.bankImplService.update(id,employeeDto);
    }
    @DeleteMapping("api/Admin/teller/{id}")
    public void removeEmployee(@PathVariable("id")Long id){
        this.bankImplService.deleteEmployee(id);
    }

 //  public boolean setManager(){};

    // AccountDto controller
    @PostMapping("api/teller/account")
    public Account createAccount(@RequestBody AccountDto accountDto){
     return this.bankImplService.add(accountDto);
    }
    @PutMapping("api/teller/account/{id}")
    public Account updateAccount(@PathVariable("id")Long id,@RequestBody AccountDto accountDto){
        return this.bankImplService.update(id,accountDto);
    };

    @DeleteMapping("api/teller/account/{id}")
    public void removeAccount(@PathVariable("id")Long id){
        this.bankImplService.deleteAccount(id);
    }

    @GetMapping("api/teller/account/{id}")
    public List<Account> getAccountsOfCustomer(@PathVariable("id") Long id){
        return this.bankImplService.findAccountsOfCustomer(id);
    }
    @GetMapping("api/teller/account/details/{id}")

    public String getAccountDetails(@PathVariable("id")Long id){
        return this.bankImplService.getAccountDetails(id);
    };

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
