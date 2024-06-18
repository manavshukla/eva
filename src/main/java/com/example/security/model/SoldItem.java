package com.example.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "soldItems")
public class SoldItem {

    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private long barCode;
    private String name;
    private double quantity;
    private String quantityUnitType;
    private double price;
    private String description;
    private long discountPercentage;
    private String paymentType;
    private String category;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateTime;
}