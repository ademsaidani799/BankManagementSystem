package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Loan;
import com.example.BankManagementSystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepositories extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByAccount_Id(Long id);

    List<Transaction> findTransactionsByAccount_Customer_Id(Long id);

}
