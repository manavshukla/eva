package com.example.security.payload.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashierRequest {

    @NotEmpty(message = "Email can not be null or empty")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format"
    )
    private String email;

    @NotEmpty(message = "Password can not be null or empty")
    @Size(min = 5, message = "Password must be at least 5 characters long.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$",
            message = "Password must be at least 5 characters long, contain at least one uppercase letter, one numeric character, and one special character."
    )
    private String password;

    @NotEmpty(message = "Name can not be null or empty")
    private String name;
}


