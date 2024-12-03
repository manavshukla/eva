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

    @NotEmpty(message = "Name can not be null")
    private String name;

    private String productDesc;

    @NotEmpty(message = "Product Type can not be null")
    private String productType;

    private String barcode;

    private String productImg;

    @Min(0)
    private Double buyingPrice;

    @Min(0)
    private Double salesPrice;

    private String quantityUnitType;

    private String category;

}


