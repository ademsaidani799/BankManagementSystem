package com.example.BankManagementSystem.DTOs;

public class BranchDto {
    private Long id;


    private String address;

    private String phone;

    public Long getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}