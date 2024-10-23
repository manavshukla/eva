package com.example.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "shops")
@NoArgsConstructor
public class Shop extends BaseEntity {
  private String corporateType;
  private String shopRegistrationData1;
  private String shopRegistrationData2;
  private String shopCity;
  private String shopName;
  private String shopAddress;
}