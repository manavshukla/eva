package com.example.security.controller;

import com.example.security.model.Procurement;
import com.example.security.model.Sale;
import com.example.security.payload.request.ProcurementRequest;
import com.example.security.payload.request.SalesRequest;
import com.example.security.service.ProcurementService;
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
@RequestMapping("/api/v1/procurements")
public class ProcurementController {

    private final ProcurementService procurementService;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProcurementRequest request) {
        procurementService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<Page<Procurement>> list(Pageable pageable) {
        Page<Procurement> page = procurementService.list(pageable);
        return ResponseEntity.ok(page);
    }

}

