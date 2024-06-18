package com.example.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.security.model.GlobalUpcBarcodes;

import java.util.List;

@Repository
public interface GlobalUpcBarcodesRepository extends JpaRepository<GlobalUpcBarcodes, Integer> {
    @Query("SELECT u FROM GlobalUpcBarcodes u")
    public List<GlobalUpcBarcodes> getGlobalUpcBarcodes();
}