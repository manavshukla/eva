package com.example.security.exception;

public class CashierException extends RuntimeException {

    public CashierException(String message) {
        super(message);
    }

    public CashierException(String message, Throwable cause) {
        super(message, cause);
    }
}