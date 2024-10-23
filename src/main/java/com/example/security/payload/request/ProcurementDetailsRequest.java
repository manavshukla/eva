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
public class ProcurementDetailsRequest {

    @NotNull
    @Min(1)
    private Double quantity;

    @NotNull
    @Min(1)
    private Long productId;

    @Min(1)
    private Double salePrice;

    @Min(1)
    private Double buyingPrice;

    private Double margin;
    private Double totalProfit;
    private Double totalWholesalePrice;

}
