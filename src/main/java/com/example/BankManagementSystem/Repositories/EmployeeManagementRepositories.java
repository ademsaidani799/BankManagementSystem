package com.example.BankManagementSystem.Repositories;

import com.example.BankManagementSystem.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeManagementRepositories extends JpaRepository<Employee,Long> {
    Employee findByUsername(String username);
    List<Employee> findEmployeesByBranch_Id(Long branchId);

}
