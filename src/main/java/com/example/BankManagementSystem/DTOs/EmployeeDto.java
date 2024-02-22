package com.example.BankManagementSystem.DTOs;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDto extends PersonDto {
    private Long id;



    public Long getId() {
        return id;
    }

    private String position;

    private EmployeeDto manager;

    private BranchDto branchDto;

    public List<GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            this.authorities = new ArrayList<>();
        }
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = //
            new ArrayList<>();

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
    public BranchDto getBranchDto() {
        return branchDto;
    }

    public void setBranchDto(BranchDto branchDto) {
        this.branchDto = branchDto;
    }
}