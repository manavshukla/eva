package com.example.security.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementRequest {

    @NotNull(message = "Total Amount can not be null or empty")
    private Double totalAmount;

    @NotEmpty(message = "Mode Of Payment can not be null or empty")
    private String modeOfPayment;

    @NotNull(message = "Purchase Date can not be null or empty")
    private LocalDate purchaseDate;

    @NotNull(message = "DistributorId can not be null or empty")
    @Min(1)
    private Long distributorId;

    @NotNull(message = "Details can not be null or empty")
    @Size(min = 1, message = "The list must contain at least 1 item")
    private List<ProcurementDetailsRequest> details;
}


