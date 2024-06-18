package com.example.security.payload.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// define snake case for the field names
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterRequest {

    private String email;
    private String password;
    private String corporateType;
    private String propertyNameField1;
    private String propertyNameField2;
    private String selectedCityId;
    private String shopName;
    private String shopAddress;
}