package com.example.BankManagementSystem.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name="teller_id")
    private Employee teller;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Employee getTeller() {
        return teller;
    }

    public void setTeller(Employee teller) {
        this.teller = teller;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", account=" + (account != null ? account.getId() : null) +
                ", teller=" + (teller != null ? teller.getId() : null) +
                ", type='" + type + '\'' +
                '}';
    }

}
