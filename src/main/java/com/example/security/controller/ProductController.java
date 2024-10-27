package com.example.security.controller;

import com.example.security.model.Product;
import com.example.security.payload.request.ProductRequest;
import com.example.security.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProductRequest request) {
        productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<Page<Product>> list(Pageable pageable) {
        Page<Product> productList = productService.list(pageable);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        Product product = productService.update(id, request);
        return ResponseEntity.ok(product);
    }
}





















//AB