package com.example.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.model.Sales;
import com.example.security.repository.SalesRepository;
import com.example.security.service.SalesService;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository repository;

    public void save(List<Sales> sales) {
        for (Sales item : sales){
            repository.save(item);
        }
    }

    public void delete(Sales sales){
        repository.delete(sales);
    }
}
