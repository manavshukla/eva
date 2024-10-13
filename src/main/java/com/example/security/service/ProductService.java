package com.example.security.service;

import com.example.security.model.Product;
import com.example.security.payload.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    void save(ProductRequest productRequest);

    Page<Product> list(Pageable pageable);

    Product getById(Long id);

    Product update(Long id, ProductRequest request);
}