package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.security.model.Products;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("SELECT l FROM Products l WHERE l.shopId = :c")
    public List<Products> getProducts(@Param("c") String shopId);
}