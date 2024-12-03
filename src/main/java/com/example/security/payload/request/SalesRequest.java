package com.example.security.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesRequest {

    @NotNull(message = "Total Amount Type can not be null")
    @Min(1)
    private Double totalAmount;
    private Double totalProfit;
    private Double cashReceived;
    private String modeOfPayment;

    @NotEmpty(message = "Details  can not be null")
    @Size(min = 1, message = "The list must contain at least 1 item")
    private List<SalesDetailsRequest> details;
}


