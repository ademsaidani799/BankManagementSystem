package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepositories extends JpaRepository<Branch,Long> {
}
