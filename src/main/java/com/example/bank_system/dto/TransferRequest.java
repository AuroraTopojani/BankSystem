package com.example.bank_system.dto;

public class TransferRequest {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
    private String reason;
    private boolean flatFee;

    // Getters and Setters
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isFlatFee() {
        return flatFee;
    }

    public void setFlatFee(boolean flatFee) {
        this.flatFee = flatFee;
    }
}
