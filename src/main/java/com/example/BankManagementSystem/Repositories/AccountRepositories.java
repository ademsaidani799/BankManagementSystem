package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositories extends JpaRepository<Account,Long> {
}
