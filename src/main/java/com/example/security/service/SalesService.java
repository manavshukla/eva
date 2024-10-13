package com.example.security.service;

import com.example.security.model.Sale;
import com.example.security.payload.request.SalesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SalesService {

    void save(SalesRequest salesRequest);

    Page<Sale> list(Pageable pageable);
}