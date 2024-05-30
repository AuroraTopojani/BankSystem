package com.example.bank_system.exception;

public class BankServiceException extends RuntimeException {
    public BankServiceException(String message) {
        super(message);
    }
}
