package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Loan;
import com.example.BankManagementSystem.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepositories extends JpaRepository<Transaction,Long> {
}
