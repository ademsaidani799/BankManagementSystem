package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerManagementRepositories extends JpaRepository<Customer,Long>{

    Optional<Customer>findByUsername(String username);
    boolean existsByUsername(String username);
}