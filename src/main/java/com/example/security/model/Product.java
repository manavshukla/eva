package com.example.security.model;

import jakarta.persistence.Entity;
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
@Table(name = "products")

public class Product extends BaseShopEntity {

  private String name;

  private String productDesc;

  private String productType;

  private String barcode;

  private String productImg;

  private Double buyingPrice;

  private Double salesPrice;

  private String quantityUnitType;

  private String category;

}