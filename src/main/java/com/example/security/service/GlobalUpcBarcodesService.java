package com.example.security.service;

import java.util.List;

import com.example.security.model.GlobalUpcBarcodes;


public interface GlobalUpcBarcodesService {

    public void save(List<GlobalUpcBarcodes> barcodes);

    public void delete(GlobalUpcBarcodes barcodes);

    public List<GlobalUpcBarcodes> getGlobalUpcBarcodes();
}