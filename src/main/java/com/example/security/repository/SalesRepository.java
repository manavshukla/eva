package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
}