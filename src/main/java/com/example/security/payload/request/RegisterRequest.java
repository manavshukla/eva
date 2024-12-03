package com.example.security.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

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

    @NotEmpty(message = "Corporate Type can not be null or empty")
    private String corporateType;
    @NotEmpty(message = "Shop RegistrationData1 can not be null or empty")
    private String shopRegistrationData1;
    @NotEmpty(message = "Shop RegistrationData2 can not be null or empty")
    private String shopRegistrationData2;
    private String shopCity;
    private String shopName;
    private String shopAddress;
}


