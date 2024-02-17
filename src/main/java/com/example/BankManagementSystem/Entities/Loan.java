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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", customer=" + (customer != null ? customer.getId() : null) +
                ", branch=" + (branch != null ? branch.getId() : null) +
                ", type='" + type + '\'' +
                ", startingDate=" + startingDate +
                ", dueDate=" + dueDate +
                ", amount=" + amount +
                '}';
    }

}
