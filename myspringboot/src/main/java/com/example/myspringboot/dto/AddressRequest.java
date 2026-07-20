package com.example.myspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequest(

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Pin code is required")
        @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Pin code must be a valid 6-digit code")
        String pinCode
) {
}