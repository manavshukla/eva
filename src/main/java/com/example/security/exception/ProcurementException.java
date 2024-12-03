package com.example.security.exception;

public class ProcurementException extends RuntimeException {
    public ProcurementException(String message) {
        super(message);
    }

    public ProcurementException(String message, Throwable cause) {
        super(message, cause);
    }
}
