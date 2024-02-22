package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Customer;
import com.example.BankManagementSystem.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepositories extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);
}
