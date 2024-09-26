package com.example.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String productDesc;
    private String productType;
    private String barcode;
    private String productImg;
    private Double buyingPrice;
    private Double salesPrice;
    private String quantityUnitType;
    private Integer category;
    private Long shopId;
}