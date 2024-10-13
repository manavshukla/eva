package com.example.security.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotEmpty
    private String name;

    private String productDesc;

    @NotEmpty
    private String productType;

    @NotEmpty
    private String barcode;

    private String productImg;

    @Min(1)
    private Double buyingPrice;

    @Min(1)
    private Double salesPrice;

    private String quantityUnitType;

    private String category;

}


