package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepositories extends JpaRepository<Customer,Long>{

    Optional<Customer>findByLogin(String login);
}