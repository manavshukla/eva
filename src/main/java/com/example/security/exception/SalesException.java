package com.example.security.exception;

public class SalesException extends RuntimeException {
    public SalesException(String message) {
        super(message);
    }

    public SalesException(String message, Throwable cause) {
        super(message, cause);
    }
}
