package com.example.security.payload.request;

import jakarta.validation.constraints.Min;
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

    @NotNull
    @Min(1)
    private Double quantity;

    @NotNull
    @Min(1)
    private Double price;
    private Double discount;
    private Double profit;
    private Long productId;

}
