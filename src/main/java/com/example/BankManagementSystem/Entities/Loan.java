package com.example.BankManagementSystem.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;

    private String type;
    private Date startingDate;

    private Date dueDate;

    private BigDecimal amount;

}
