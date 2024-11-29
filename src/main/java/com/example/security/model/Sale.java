package com.example.security.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseShopEntity{

    @Id
    @GeneratedValue
    private Long id;

    private Double totalAmount;
    private Double totalProfit;
    private Double cashReceived;
    private String modeOfPayment;

    @OneToMany(mappedBy = "sale")
    private Set<SalesDetail> details;

    private Long createdBy;

}