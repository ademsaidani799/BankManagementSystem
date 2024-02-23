package com.example.BankManagementSystem;

import com.example.BankManagementSystem.DTOs.BranchDto;
import com.example.BankManagementSystem.DTOs.CustomerDto;
import com.example.BankManagementSystem.Entities.Branch;
import com.example.BankManagementSystem.Repositories.*;
import com.example.BankManagementSystem.Services.BankImplService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankImplServiceTest {


    BankImplService service;

    @Mock
    AccountRepositories accountRepositories;

    @Mock
    BranchRepositories branchRepositories;

    @Mock
    CustomerManagementRepositories customerManagementRepositories;

    @Mock
    CustomerRepositories customerRepositories;

    @Mock
    EmployeeManagementRepositories employeeManagementRepositories;

    @Mock
    LoanRepositories loanRepositories;

    @Mock
    TransactionRepositories transactionRepositories;

    @BeforeEach
    void setUp() {
        // Note: You need to pass all mocked repositories to the service constructor
        this.service = new BankImplService(
                customerRepositories,
                accountRepositories,
                branchRepositories,
                customerManagementRepositories,
                employeeManagementRepositories,
                loanRepositories,
                transactionRepositories
        );
    }

    @Test
    void getBranchDetailShouldWork() {
// given
        Branch branch = new Branch();
        branch.setAddress("Main Street, City Center");
        branch.setPhone("555-1234");
        branch.setId(1L);
        when(branchRepositories.findById(1L)).thenReturn(Optional.of(branch));
// when
        String branchDetails = service.getBranchDetails(1L).toString();
// then
        assertThat(branchDetails).isEqualTo("Branch{id=1, address='Main Street, City Center', phone='555-1234'}");
    }

    @Test
    void creatingANewBranchShouldReturnTheSameData() {
// given
        Branch branch = new Branch();
        branch.setAddress("Main Street, City Center");
        branch.setPhone("555-1234");
        branch.setId(1L);
        given(branchRepositories.saveAndFlush(any(Branch.class)))
                .willReturn(branch);
// when
        BranchDto branchDto = new BranchDto();
        branchDto.setAddress("Main Street, City Center");
        branchDto.setPhone("555-1234");
        Branch newBranch = service.addBranch(branchDto);
// then
        assertThat(newBranch.getId()).isEqualTo(1);
        assertThat(newBranch.getAddress()).isEqualTo("Main Street, City Center");
        assertThat(newBranch.getPhone()).isEqualTo("555-1234");
    }

    @Test
    void deletingABranchShouldWork() {
// given
        Branch branch = new Branch();
        branch.setAddress("Main Street, City Center");
        branch.setPhone("555-1234");
        branch.setId(1L);
        when(branchRepositories.existsById(1L)).thenReturn(true);
// when
        service.deleteBranch(1L);
// then
        verify(branchRepositories).deleteById(1L);
    }

    // Add your test cases here
}
