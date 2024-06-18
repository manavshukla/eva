package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.security.model.LocalUpcBarcodes;

import java.util.List;

@Repository
public interface LocalUpcBarcodesRepository extends JpaRepository<LocalUpcBarcodes, Integer> {
    @Query("SELECT l FROM LocalUpcBarcodes l WHERE l.companyCode = :c")
    public List<LocalUpcBarcodes> getLocalUpcBarcodes(@Param("c") String companyCode);
}