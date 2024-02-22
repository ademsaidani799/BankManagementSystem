package com.example.BankManagementSystem.Services;


import com.example.BankManagementSystem.DTOs.*;
import com.example.BankManagementSystem.Entities.*;
import com.example.BankManagementSystem.Interfaces.Bank;
import com.example.BankManagementSystem.Repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BankImplService implements Bank {

    private final AccountRepositories accountRepositories;
    private final BranchRepositories branchRepositories;
    private final CustomerManagementRepositories customerManagementRepositories;
    private final CustomerRepositories customerRepositories;
    private final EmployeeManagementRepositories employeeManagementRepositories;
    private final LoanRepositories loanRepositories;
    private final TransactionRepositories transactionRepositories;

    public BankImplService( CustomerRepositories customerRepositories,AccountRepositories accountRepositories, BranchRepositories branchRepositories, CustomerManagementRepositories customerManagementRepositories, EmployeeManagementRepositories employeeManagementRepositories, LoanRepositories loanRepositories, TransactionRepositories transactionRepositories) {
        this.accountRepositories = accountRepositories;
        this.branchRepositories = branchRepositories;
        this.customerManagementRepositories = customerManagementRepositories;
        this.employeeManagementRepositories = employeeManagementRepositories;
        this.loanRepositories = loanRepositories;
        this.transactionRepositories = transactionRepositories;
        this.customerRepositories=customerRepositories;
    }

    // Implementation of Bank methods

    //Implementation of Create
    public Customer add(CustomerDto customerDto) throws Exception {
        // Check if the username already exists
        if (customerManagementRepositories.existsByUsername(customerDto.getUsername())) {
            // Username is already taken, handle accordingly (throw an exception, return an error response, etc.)
            throw new Exception();
        }

        // Create a new Customer
        Customer customer = new Customer(customerDto.getUsername(), customerDto.getPassword(), "ROLE_USER");

        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customer.setRegistrationDate(new Date());

        // Save the new customer
        return customerManagementRepositories.saveAndFlush(customer);
    }


    public Employee add(EmployeeDto employeeDto) {
        // Fetch Branch
        Branch branchTemp = branchRepositories.findByAddress(employeeDto.getBranch().getAddress());

        // Fetch Manager if provided
        Employee manager = null;
        if (employeeDto.getManager() != null) {
            String username = employeeDto.getManager().getUsername();

            manager = employeeManagementRepositories.findByUsername(employeeDto.getManager().getUsername());
        }

        // Create new Employee
        Employee employee = new Employee(employeeDto.getUsername(),employeeDto.getPassword(),"ROLE_TELLER");

        employee.setName(employeeDto.getName());
        employee.setPhone(employeeDto.getPhone());
        employee.setEmail(employeeDto.getEmail());
        employee.setPosition(employeeDto.getPosition());
        employee.setRegistrationDate(new Date());

        // Set Branch and Manager for the new Employee
        employee.setBranch(branchTemp);
        employee.setManager(manager);

        // Save and return the new Employee
        return employeeManagementRepositories.saveAndFlush(employee);
    }

    public Account add(AccountDto accountDto) throws Exception {
        try {
            Customer customerTemp = customerRepositories.findByUsername(accountDto.getCustomer().getUsername());
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
            account.setCurrentBalance(accountDto.getCurrentBalance());
            account.setInterestRate(accountDto.getInterestRate());
            account.setType(accountDto.getType());

            // Add the new account to the existing list of accounts
            accounts.add(account);

            // Update the customer's accounts list
            customerTemp.setAccounts(accounts);
            customerManagementRepositories.saveAndFlush(customerTemp);
            return accountRepositories.saveAndFlush(account);
        } catch (Exception e) {
            // Handle the exception accordingly (throw a specific exception, log the error, return an error response, etc.)
            // For example, you can throw a custom exception like AccountCreationException
            throw new Exception();
        }
    }


    public Loan addLoan(LoanDto loanDto) {

        Optional<Customer> customerTemp = customerManagementRepositories.findByUsername(loanDto.getCustomerDto().getUsername());
        Branch branchTemp = branchRepositories.findByAddress(loanDto.getBranchDto().getAddress());

        // Check if the customer already has a list of loans
        List<Loan> loans = customerTemp.get().getLoans();
        if (loans == null) {
            loans = new ArrayList<>();
        }

        Loan loan = new Loan();
        loan.setCustomer(customerTemp.get());
        loan.setBranch(branchTemp);
        loan.setStartingDate(new Date());
        loan.setType(loanDto.getType());
        loan.setDueDate(loanDto.getDueDate());
        loan.setAmount(loanDto.getAmount());

        // Add the new loan to the existing list of loans
        loans.add(loan);

        // Update the customer's loans list
        customerTemp.get().setLoans(loans);

        customerManagementRepositories.saveAndFlush(customerTemp.get());
        return loanRepositories.saveAndFlush(loan);
    }

    public Branch addBranch(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setAddress(branchDto.getAddress());
        branch.setPhone(branchDto.getPhone());

        return branchRepositories.saveAndFlush(branch);
    }
    public Transaction addTransaction(TransactionDto transactionDto) {
        Account accountTemp = accountRepositories.findById(transactionDto.getAccount().getId()).orElse(null);
        Employee tellerTemp = employeeManagementRepositories.findById(transactionDto.getTeller().getId()).orElse(null);

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

    //Implementation of read
    public List<Customer> findAllCustomers(){
       return customerManagementRepositories.findAll();
    }

    public Customer findCustomer(long id){
        return customerManagementRepositories.findById(id).get();
    }

    public List<Employee> findAllEmployees(){
        return employeeManagementRepositories.findAll();
    }
    public Employee findEmployee(Long id){
        return employeeManagementRepositories.findById(id).get();

    }



    public List<Account> findAccountsOfCustomer(Long customerId) {
        // Retrieve the customer by ID
        Customer customer = customerManagementRepositories.findById(customerId).orElse(null);

        if (customer != null) {
            // Return the list of accounts associated with the customer
            return customer.getAccounts();
        } else {
            // Handle the case where the customer is not found
            // You might throw an exception or handle it based on your application logic
            // For example:
            throw new EntityNotFoundException("Customer not found");
        }
    }

    public List<Loan> findLoansOfCustomer(Long customerId) {
        Customer customer = customerManagementRepositories.findById(customerId).orElse(null);

        if (customer != null) {
            return customer.getLoans();
        } else {
            // Handle the case where the customer is not found
            // You might throw an exception or handle it based on your application logic
            // For example:
            throw new EntityNotFoundException("Customer not found");
        }
    }

    public List<Transaction> findTransactionsOfAccount(Long id){
        return transactionRepositories.findTransactionsByAccount_Id(id);
    }

    public List<Transaction> findTransactionsOfCustomer(Long id) {
        return transactionRepositories.findTransactionsByAccount_Customer_Id(id);
    }

    public String getTransactionDetails(Long id){
        Optional<Transaction> transaction = transactionRepositories.findById(id);

        if (transaction.isPresent()){return transaction.get().toString();}
        return "No such transaction found!";
    }
    public String getAccountDetails(Long id){
        Optional<Account> account = accountRepositories.findById(id);
        if (account.isPresent()){return account.get().toString();}
        return "No such account found!";

    }
    public String getLoanDetails(Long id){
        Optional<Loan> loan = loanRepositories.findById(id);
        if (loan.isPresent()){return loan.get().toString();}
        return "No such loan found!";

    }
    public String getBranchDetails(Long id){
        Optional<Branch> branch = branchRepositories.findById(id);
        if (branch.isPresent()){return branch.get().toString();}
        return "No such branch found!";

    }
    public List<Employee> getBranchEmployees(Long branchId){
return employeeManagementRepositories.findEmployeesByBranch_Id(branchId);

    }
    //Implementation of update
    public Account update(Long accountId, AccountDto accountDto) {
        // Retrieve the existing account from the database
        Optional<Account> optionalAccount = accountRepositories.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();

            // Update the fields with the new values from the DTO
            existingAccount.setType(accountDto.getType());
            existingAccount.setOpeningDate(accountDto.getOpeningDate());
            existingAccount.setCurrentBalance(accountDto.getCurrentBalance());
            existingAccount.setInterestRate(accountDto.getInterestRate());

            // Retrieve and set the customer from the DTO
            String customerLogin = accountDto.getCustomer().getUsername();
            if (customerLogin != null) {
                Customer customer = customerManagementRepositories.findByUsername(customerLogin).get();
                if(customer!=null){
                    existingAccount.setCustomer(customer);
                }
            }

            // Retrieve and set the branch from the DTO
            Long branchId = accountDto.getBranch().getId();
            if (branchId != null) {
                Optional<Branch> optionalBranch = branchRepositories.findById(branchId);
                optionalBranch.ifPresent(existingAccount::setBranch);
            }

            // Save the updated account
            return accountRepositories.saveAndFlush(existingAccount);
        } else {
            // Handle the case where the account with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            return null;
        }
    }

    public Customer update(Long customerId, CustomerDto customerDto) {
        // Retrieve the existing customer from the database
        Optional<Customer> optionalCustomer = customerManagementRepositories.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Update the fields with the new values from the DTO
            existingCustomer.setUsername(customerDto.getUsername());
            existingCustomer.setPassword(customerDto.getPassword());
            existingCustomer.setName(customerDto.getName());
            existingCustomer.setPhone(customerDto.getPhone());
            existingCustomer.setEmail(customerDto.getEmail());

            // Save the updated customer
            return customerManagementRepositories.saveAndFlush(existingCustomer);
        } else {
            // Handle the case where the customer with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            return null;
        }
    }
    public Employee update(Long employeeId, EmployeeDto employeeDto) {
        // Retrieve the existing employee from the database
        Optional<Employee> optionalEmployee = employeeManagementRepositories.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            // Fetch Branch
            Branch branchTemp = branchRepositories.findByAddress(employeeDto.getBranch().getAddress());

            // Fetch Manager if provided
            Employee manager = null;
            if (employeeDto.getManager() != null) {
                manager = employeeManagementRepositories.findByUsername(employeeDto.getManager().getUsername());
            }

            // Update the fields with the new values from the DTO
            existingEmployee.setUsername(employeeDto.getUsername());
            existingEmployee.setPassword(employeeDto.getPassword());
//            existingEmployee.setName(employeeDto.getName());
//            existingEmployee.setPhone(employeeDto.getPhone());
//            existingEmployee.setEmail(employeeDto.getEmail());

            // Set Branch and Manager for the existing Employee
            existingEmployee.setBranch(branchTemp);
            existingEmployee.setManager(manager);

            // Save the updated employee
            return employeeManagementRepositories.saveAndFlush(existingEmployee);
        } else {
            // Handle the case where the employee with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            return null;
        }
    }
    public Loan update(Long loanId, LoanDto loanDto) {
        // Retrieve the existing loan from the database
        Optional<Loan> optionalLoan = loanRepositories.findById(loanId);

        if (optionalLoan.isPresent()) {
            Loan existingLoan = optionalLoan.get();

            Customer customerTemp = customerManagementRepositories.findByUsername(loanDto.getCustomerDto().getUsername()).get();
            Branch branchTemp = branchRepositories.findByAddress(loanDto.getBranchDto().getAddress());

            // Update the fields with the new values from the DTO
            existingLoan.setCustomer(customerTemp);
            existingLoan.setBranch(branchTemp);
            existingLoan.setType(loanDto.getType());
            existingLoan.setDueDate(loanDto.getDueDate());
            existingLoan.setAmount(loanDto.getAmount());

            // Save the updated loan
            return loanRepositories.saveAndFlush(existingLoan);
        } else {
            // Handle the case where the loan with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            return null;
        }

    }


    public Branch update(Long branchId, BranchDto branchDto) {
        // Retrieve the existing branch from the database
        Optional<Branch> optionalBranch = branchRepositories.findById(branchId);

        if (optionalBranch.isPresent()) {
            Branch existingBranch = optionalBranch.get();

            // Update the fields with the new values from the DTO
            existingBranch.setAddress(branchDto.getAddress());
            existingBranch.setPhone(branchDto.getPhone());

            // Save the updated branch
            return branchRepositories.saveAndFlush(existingBranch);
        } else {
            // Handle the case where the branch with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            return null;
        }
    }

    //Implementation of delete

    public void deleteCustomer(Long customerId) {
        // Check if the customer with the given ID exists before attempting to delete
        if (customerManagementRepositories.existsById(customerId)) {
            // Delete the customer (and associated accounts and loans due to cascading)
            customerManagementRepositories.deleteById(customerId);
        } else {
            // Handle the case where the customer with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            throw new EntityNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    public void deleteEmployee(Long employeeId) {
        // Check if the employee with the given ID exists before attempting to delete
        if (employeeManagementRepositories.existsById(employeeId)) {
            // Delete the employee (and associated branch due to cascading)
            employeeManagementRepositories.deleteById(employeeId);
        } else {
            // Handle the case where the employee with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }

    public void deleteAccount(Long id) {
        try {
            // Attempt to delete the account
            accountRepositories.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the account with the given ID is not found
            throw new EntityNotFoundException("Account with ID " + id + " not found");
        }
    }

    public void deleteLoan(Long id) {
        try {
            // Attempt to delete the loan
            loanRepositories.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the loan with the given ID is not found
            throw new EntityNotFoundException("Loan with ID " + id + " not found");
        }
    }

    public void deleteBranch(Long id) {
        // Check if the branch with the given ID exists before attempting to delete
        if (branchRepositories.existsById(id)) {
            // Delete the branch
            branchRepositories.deleteById(id);
        } else {
            // Handle the case where the branch with the given ID is not found
            // You might want to throw an exception or handle it based on your application's logic
            throw new EntityNotFoundException("Branch with ID " + id + " not found");
        }
    }
public String withdraw(Long accountId, Authentication authentication, BigDecimal amount,boolean isWithdraw){
    Optional<Account> optionalAccount = accountRepositories.findById(accountId);
    Account existingAccount = optionalAccount.get();
 if(existingAccount.getCustomer().getName().equalsIgnoreCase(authentication.getName())){
    if(existingAccount.getCurrentBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0){
        existingAccount.setCurrentBalance(existingAccount.getCurrentBalance().subtract(amount));
      Transaction transaction = new Transaction();
      transaction.setDate(new Date());
      transaction.setAmount(amount);
      transaction.setAccount(existingAccount);
      if(isWithdraw){
      transaction.setType("Withdraw");}else {transaction.setType("Transfer(sended)");}
      transactionRepositories.saveAndFlush(transaction);
       accountRepositories.saveAndFlush(existingAccount);
        return "Success withdraw";
    }else{
        return "No enough balance";
    }
 }
return "error";

}
    public String deposit(Long accountId, Authentication authentication,BigDecimal amount){
        Optional<Account> optionalAccount = accountRepositories.findById(accountId);
        Employee teller = employeeManagementRepositories.findByUsername(authentication.getName());
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();

            // Update balance using setBalance instead of add
            existingAccount.setCurrentBalance(existingAccount.getCurrentBalance().add(amount));
            Transaction transaction = new Transaction();
            transaction.setDate(new Date());
            transaction.setAmount(amount);
            transaction.setAccount(existingAccount);
            transaction.setTeller(teller);
            transaction.setType("Deposit");
            transactionRepositories.saveAndFlush(transaction);
            accountRepositories.saveAndFlush(existingAccount);
            return "Deposit succeeded";
        } else {
            // Handle the case when the account is not found
            // You can throw an exception, log an error, or take other appropriate actions
            // For example:
            throw new RuntimeException("Account not found for ID: " + accountId);
        }

    }

    public String transfer(Authentication authentication,Long fromAccountId, Long toAccountId, BigDecimal amount){
        try {
            // Withdraw from the source account
            String withdrawResult = withdraw(fromAccountId, authentication,amount,false);

            // If withdrawal is successful, deposit into the destination account
            if (withdrawResult.equals("Success withdraw")) {


                Optional<Account> optionalAccount = accountRepositories.findById(toAccountId);
                if (optionalAccount.isPresent()) {
                    Account existingAccount = optionalAccount.get();

                    // Update balance using setBalance instead of add
                    existingAccount.setCurrentBalance(existingAccount.getCurrentBalance().add(amount));
                    Transaction transaction = new Transaction();
                    transaction.setDate(new Date());
                    transaction.setAmount(amount);
                    transaction.setAccount(existingAccount);
                    transaction.setType("Transfer(received)");
                    transactionRepositories.saveAndFlush(transaction);
                    accountRepositories.saveAndFlush(existingAccount);
                    return "Transfer succeeded";
                } else {
                    // Handle the case when the account is not found
                    // You can throw an exception, log an error, or take other appropriate actions
                    // For example:
                    throw new RuntimeException("Account not found for ID: " + toAccountId);
                }


            } else {
                // Handle the case when withdrawal fails (e.g., not enough balance)
                return "Transfer failed: " + withdrawResult;
            }
        } catch (RuntimeException e) {
            // Handle exceptions thrown during withdrawal (e.g., account not found)
            return "Transfer failed: " + e.getMessage();
        }
    }

}