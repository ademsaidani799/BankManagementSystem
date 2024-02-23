package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositories extends JpaRepository<Employee, Long> {
    Employee findByUsername(String username);
}
