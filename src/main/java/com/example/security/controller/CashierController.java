package com.example.security.controller;

import com.example.security.model.Cashier;
import com.example.security.model.Sale;
import com.example.security.payload.request.CashierRequest;
import com.example.security.payload.request.SalesRequest;
import com.example.security.service.CashierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cashiers")
public class CashierController {

    private final CashierService cashierService;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CashierRequest request) {
        cashierService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<Page<Cashier>> list(Pageable pageable) {
        Page<Cashier> page = cashierService.list(pageable);
        return ResponseEntity.ok(page);
    }

}

