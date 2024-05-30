package com.example.bank_system.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double balance;

    @OneToMany(mappedBy = "originatingAccount")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "resultingAccount")
    private List<Transaction> resultingTransactions = new ArrayList<>();

    // No-argument constructor
    public Account() {
    }

    // Getters and Setters
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getResultingTransactions() {
        return resultingTransactions;
    }

    public void setResultingTransactions(List<Transaction> resultingTransactions) {
        this.resultingTransactions = resultingTransactions;
    }
}
