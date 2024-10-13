package com.example.security.controller;

import com.example.security.model.Product;
import com.example.security.model.Sale;
import com.example.security.payload.request.SalesRequest;
import com.example.security.service.SalesService;
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
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService salesService;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SalesRequest request) {
        salesService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<Page<Sale>> list(Pageable pageable) {
        Page<Sale> page = salesService.list(pageable);
        return ResponseEntity.ok(page);
    }

}

