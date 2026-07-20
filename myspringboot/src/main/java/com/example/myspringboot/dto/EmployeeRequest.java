package com.example.myspringboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid email address")
        String email,

        String department,

        //@PositiveOrZero(message = "Salary must be zero or positive")
        Double salary,

        @NotNull(message = "Address is required")
        @Valid
        AddressRequest address
) {
}
