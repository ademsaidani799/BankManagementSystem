package com.example.BankManagementSystem.DTOs;

import java.math.BigDecimal;
import java.util.Date;

public class LoanDto {

    private int id;

    private CustomerDto customerDto;

    private BranchDto branchDto;

    private Date startingDate;

    private Date dueDate;

    private BigDecimal amount;

}
