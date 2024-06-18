package com.example.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.example.security.model.LocalUpcBarcodes;
import com.example.security.repository.LocalUpcBarcodesRepository;
import com.example.security.service.LocalUpcBarcodesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalUpcBarcodesServiceImpl implements LocalUpcBarcodesService{

    @Autowired
    private LocalUpcBarcodesRepository repository;

    public void save(List<LocalUpcBarcodes> barcodes) {
        for (LocalUpcBarcodes item : barcodes){
            repository.save(item);
        }
    }

    public void delete(LocalUpcBarcodes barcodes){
        repository.delete(barcodes);
    }

    public List<LocalUpcBarcodes> getLocalUpcBarcodes(String companyCode) {
        return repository.getLocalUpcBarcodes(companyCode);
    }
}
