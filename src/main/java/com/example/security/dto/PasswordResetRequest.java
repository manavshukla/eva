package com.example.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PasswordResetRequest {
    @JsonProperty("new-password")
    private String newPassword;
}
