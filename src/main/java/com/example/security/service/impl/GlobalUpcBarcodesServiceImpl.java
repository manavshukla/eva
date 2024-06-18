package com.example.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.example.security.model.GlobalUpcBarcodes;
import com.example.security.repository.GlobalUpcBarcodesRepository;
import com.example.security.service.GlobalUpcBarcodesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalUpcBarcodesServiceImpl implements GlobalUpcBarcodesService {

    @Autowired
    private GlobalUpcBarcodesRepository repository;

    public void save(List<GlobalUpcBarcodes> barcodes) {
        for (GlobalUpcBarcodes item : barcodes){
            repository.save(item);
        }
    }

    public void delete(GlobalUpcBarcodes barcodes){
        repository.delete(barcodes);
    }

    public List<GlobalUpcBarcodes> getGlobalUpcBarcodes() {
        List<GlobalUpcBarcodes> allGlobalUpcBarcodes = new ArrayList<>();
        Streamable.of(repository.getGlobalUpcBarcodes()).forEach(allGlobalUpcBarcodes::add);
        return allGlobalUpcBarcodes;
    }
}
