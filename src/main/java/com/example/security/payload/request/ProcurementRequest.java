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

  @NotNull
  @Min(1)
  private Double totalAmount;

  @NotEmpty
  private String modeOfPayment;

  @NotNull
  private LocalDate purchaseDate;

  @NotNull
  @Min(1)
  private Long distributorId;

  @NotNull
  @Size(min = 1, message = "The list must contain at least 1 item")
  private List<ProcurementDetailsRequest> details;
}


