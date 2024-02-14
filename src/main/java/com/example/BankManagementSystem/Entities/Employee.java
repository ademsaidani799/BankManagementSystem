package com.example.BankManagementSystem.Entities;

import jakarta.persistence.*;

public class Employee extends Person {

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String position;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employee manager;
    @ManyToOne
    @JoinColumn(name="branch_id")
    private Branch branch;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}