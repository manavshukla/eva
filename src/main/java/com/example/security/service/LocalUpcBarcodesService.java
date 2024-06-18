package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.example.security.model.LocalUpcBarcodes;
import com.example.security.repository.LocalUpcBarcodesRepository;

import java.util.ArrayList;
import java.util.List;

public interface LocalUpcBarcodesService {

    public void save(List<LocalUpcBarcodes> barcodes);

    public void delete(LocalUpcBarcodes barcodes);

    public List<LocalUpcBarcodes> getLocalUpcBarcodes(String companyCode);
}