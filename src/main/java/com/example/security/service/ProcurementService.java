package com.example.security.service;

import com.example.security.model.Procurement;
import com.example.security.model.Sale;
import com.example.security.payload.request.ProcurementRequest;
import com.example.security.payload.request.SalesRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProcurementService {

  void save(ProcurementRequest procurementRequest);

  Page<Procurement> list(Pageable pageable);
}