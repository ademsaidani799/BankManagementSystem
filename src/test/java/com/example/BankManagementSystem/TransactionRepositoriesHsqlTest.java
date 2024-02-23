package com.example.BankManagementSystem;

import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Branch;
import com.example.BankManagementSystem.Entities.Customer;
import com.example.BankManagementSystem.Entities.Transaction;
import com.example.BankManagementSystem.Repositories.AccountRepositories;
import com.example.BankManagementSystem.Repositories.BranchRepositories;
import com.example.BankManagementSystem.Repositories.CustomerRepositories;
import com.example.BankManagementSystem.Repositories.TransactionRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



    @DataJpaTest
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

    public class TransactionRepositoriesHsqlTest {
        @Autowired
        TransactionRepositories transactionRepositories;
        @Autowired

        CustomerRepositories customerRepositories;
        @Autowired

        AccountRepositories accountRepositories;

//        public TransactionRepositoriesHsqlTest(CustomerRepositories customerRepositories,
//                                               AccountRepositories accountRepositories) {
//            this.customerRepositories = customerRepositories;
//            this.accountRepositories = accountRepositories;
//        }

        @BeforeEach

        void setUp() {
            // Create Customer
            Customer customer = new Customer();
            customer.setId(1L);

            // Create Account and associate it with the Customer
            Account account = new Account();
            account.setId(1L);
            account.setCustomer(customer);

            // Create Transaction and associate it with the Account
            Transaction transaction = new Transaction();
            transaction.setAccount(account);

            // Set other properties for the transaction
            transaction.setDate(new Date());
            transaction.setAmount(null);
            transaction.setTeller(null);
            transaction.setType("test");

            // Save the entities
            customerRepositories.save(customer); // Make sure to save the customer first
            accountRepositories.save(account);   // Save the account with the associated customer
            transactionRepositories.save(transaction);
            }
        @Test
        void findTransactionsByAccount_IdShouldWork() {
            List<Transaction> transactions = transactionRepositories.findTransactionsByAccount_Id(1L);
            assertThat(transactions).hasSize(1);
            assertThat(transactions) //
                    .extracting(Transaction::getType) //
                    .contains("test");
        }
  @Test
        void findTransactionsByAccount_Customer_IdShouldWork() {
            List<Transaction> transactions = transactionRepositories.findTransactionsByAccount_Customer_Id(1L);
            assertThat(transactions).hasSize(1);
      assertThat(transactions) //
              .extracting(Transaction::getType) //
              .contains("test");
        }

    }



//        @Test
//        void findByNameShouldRetrieveOneEntry() {
//
//            Branch branch = branchRepositories //
//                    .findByAddress("Main Street, City Center");
//            assertThat(branch.getPhone()).isEqualTo("555-1234555");
//        }
