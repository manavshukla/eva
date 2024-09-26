package com.example.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.Products;
import com.example.security.repository.ProductsRepository;
import com.example.security.service.ProductsService;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository repository;

    public void save(List<Products> barcodes) {
        for (Products item : barcodes){
            repository.save(item);
        }
    }

    public void delete(Products barcodes){
        repository.delete(barcodes);
    }

    public List<Products> getProducts(String shopId) {
        return repository.getProducts(shopId);
    }
}
