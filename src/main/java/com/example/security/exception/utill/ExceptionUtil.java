package com.example.security.exception.utill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import java.util.Map;

@Component
@Slf4j
public class ExceptionUtil {

    public static ResponseEntity<Map<String, String>> createErrorResponse(Exception ex, HttpStatus status) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, status);
    }

}
