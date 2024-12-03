package com.example.security.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesDetailsRequest {

    @NotEmpty(message = "Quantity can not be null")
    @Min(0)
    private Double quantity;

    @NotEmpty(message = "Price can not be null")
    @Min(0)
    private Double price;
    private Double discount;
    private Double profit;
    @NotEmpty(message = "ProductId can not be null")
    private Long productId;

}
