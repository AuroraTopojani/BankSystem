package com.example.bank_system.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double flatFee;
    private double percentFee;
    private double totalTransactionFees;
    private double totalTransferAmount;

    @OneToMany
    private List<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFlatFee() {
        return flatFee;
    }

    public void setFlatFee(double flatFee) {
        this.flatFee = flatFee;
    }

    public double getPercentFee() {
        return percentFee;
    }

    public void setPercentFee(double percentFee) {
        this.percentFee = percentFee;
    }

    public double getTotalTransactionFees() {
        return totalTransactionFees;
    }

    public void setTotalTransactionFees(double totalTransactionFees) {
        this.totalTransactionFees = totalTransactionFees;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
