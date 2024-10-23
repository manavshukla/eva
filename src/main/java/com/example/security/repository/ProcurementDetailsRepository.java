package com.example.security.repository;

import com.example.security.model.ProcurementDetail;
import com.example.security.model.SalesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcurementDetailsRepository extends JpaRepository<ProcurementDetail, Long> {
}