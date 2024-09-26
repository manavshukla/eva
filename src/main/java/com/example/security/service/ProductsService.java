package com.example.security.service;

import com.example.security.model.Products;

import java.util.List;

public interface ProductsService {

    public void save(List<Products> barcodes);

    public void delete(Products barcodes);

    public List<Products> getProducts(String companyCode);
}