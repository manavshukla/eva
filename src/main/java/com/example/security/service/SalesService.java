package com.example.security.service;

import java.util.List;

import com.example.security.model.Sales;

public interface SalesService {

    public void save(List<Sales> sales);

    public void delete(Sales sales);
}