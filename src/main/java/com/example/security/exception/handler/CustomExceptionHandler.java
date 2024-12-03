package com.example.security.exception.handler;

import com.example.security.exception.*;
import com.example.security.exception.utill.ExceptionUtil;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
@Order(1)
public class CustomExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CashierException.class)
    public ResponseEntity<Map<String, String>> handleCashierException(CashierException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DemoException.class)
    public ResponseEntity<Map<String, String>> handleDemoException(DemoException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Map<String, String>> handleEmailException(EmailException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<Map<String, String>> handleProductException(ProductException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProcurementException.class)
    public ResponseEntity<Map<String, String>> handleProcurementException(ProcurementException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SalesException.class)
    public ResponseEntity<Map<String, String>> handleSalesException(SalesException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShopException.class)
    public ResponseEntity<Map<String, String>> handleShopException(ShopException ex, WebRequest webRequest) {
        return ExceptionUtil.createErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }


}
