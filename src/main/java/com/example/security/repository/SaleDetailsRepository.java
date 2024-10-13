package com.example.security.repository;

import com.example.security.model.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailsRepository extends JpaRepository<SalesDetail, Long> {
}