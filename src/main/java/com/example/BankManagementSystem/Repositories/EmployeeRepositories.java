package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Account;
import com.example.BankManagementSystem.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepositories extends JpaRepository<Employee,Long> {
    Employee findByLogin(String login);
    List<Employee> findEmployeesByBranch_Id(Long branchId);
}
