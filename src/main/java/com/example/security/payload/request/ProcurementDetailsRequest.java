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
public class ProcurementDetailsRequest {

    @NotNull(message = "Quantity can not be null or empty")
    @Min(0)
    private Double quantity;

    @NotNull(message = "ProductId can not be null or empty")
    @Min(1)
    private Long productId;

    @Min(0)
    private Double salePrice;

    @Min(1)
    private Double buyingPrice;

    private Double margin;
    private Double totalProfit;
    private Double totalWholesalePrice;

}
