package com.example.security.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GlobalUpcBarcodes")
public class GlobalUpcBarcodes {

    @Id
    @GeneratedValue
    private long id;
    private long upcean;
    private String name;
    private long categoryId;
    private String categoryName;
    private long brandId;
    private String brandName;

}