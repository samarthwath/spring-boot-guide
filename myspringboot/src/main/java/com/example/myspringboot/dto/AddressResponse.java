package com.example.myspringboot.dto;

public record AddressResponse(
        String city,
        String state,
        String pinCode
) {
}