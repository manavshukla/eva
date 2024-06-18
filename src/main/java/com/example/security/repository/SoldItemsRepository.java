package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.security.model.SoldItem;

@Repository
public interface SoldItemsRepository extends JpaRepository<SoldItem, Integer> {
}