package com.example.security.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Double totalAmount;
    private Double totalProfit;
    private Double cashReceived;
    private String modeOfPayment;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateTime;

    @OneToMany(mappedBy = "sale")
    private Set<SalesDetail> details;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    Shop shop;
}