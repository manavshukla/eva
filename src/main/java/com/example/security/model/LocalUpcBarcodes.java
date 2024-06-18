package com.example.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocalUpcBarcodes {

    @Id
    @GeneratedValue
    private long id;
    private long upcean;
    private String name;
    private long categoryId;
    private String categoryName;
    private long brandId;
    private String brandName;
    private String companyCode;
    private Double price;
    private String quantityUnitType;
    private Boolean specialProductTag;
}