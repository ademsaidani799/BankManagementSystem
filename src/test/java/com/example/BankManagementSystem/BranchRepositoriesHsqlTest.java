package com.example.BankManagementSystem;

import com.example.BankManagementSystem.Entities.Branch;
import com.example.BankManagementSystem.Repositories.BranchRepositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BranchRepositoriesHsqlTest {
    @Autowired
    BranchRepositories branchRepositories;

    @BeforeEach
    void setUp() {
        Branch branch1 = new Branch();
        branch1.setAddress("Main Street, City Center");
        branch1.setPhone("555-1234555");
        Branch branch2 = new Branch();
        branch2.setAddress("Main Street, City Center2");
        branch2.setPhone("555-123444");
        branchRepositories.saveAll( //
                List.of(branch1, branch2));

    }

    @Test
    void findAllShouldProduceAllBranches() {
        List<Branch> branches = branchRepositories.findAll();
        assertThat(branches).hasSize(2);
    }

    @Test
    void findByNameShouldRetrieveOneEntry() {

        Branch branch = branchRepositories //
                .findByAddress("Main Street, City Center");
        assertThat(branch.getPhone()).isEqualTo("555-1234555");
    }
}