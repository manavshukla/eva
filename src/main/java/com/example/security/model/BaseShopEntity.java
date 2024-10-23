package com.example.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TenantId;

@Getter
@Setter
@MappedSuperclass
public class BaseShopEntity extends BaseEntity {

  @TenantId
  @Column(name = "shop_id")
  private String shop;

}
