package com.example.BankManagementSystem.DTOs;

public class EmployeeDto extends PersonDto {

    private String position;

    private EmployeeDto manager;

    private BranchDto branchDto;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EmployeeDto getManager() {
        return manager;
    }

    public void setManager(EmployeeDto manager) {
        this.manager = manager;
    }

    public BranchDto getBranch() {
        return branchDto;
    }

    public void setBranch(BranchDto branchDto) {
        this.branchDto = branchDto;
    }
}