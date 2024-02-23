package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepositories extends JpaRepository<Loan, Long> {
}
