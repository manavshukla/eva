package com.example.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class PasswordResetRequest {
    @JsonProperty("newPassword")
    @NotEmpty(message= "New Password can not be null or empty")
    private String newPassword;
}
