package com.example.security.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashierRequest {

  @NotEmpty
  @NotNull
  @Email
  private String email;

  @NotEmpty
  @NotNull
  private String password;

  @NotEmpty
  @NotNull
  private String name;
}


