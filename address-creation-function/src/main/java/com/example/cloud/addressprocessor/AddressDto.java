package com.example.cloud.addressprocessor;

public record AddressDto(
        String street,
        String city,
        String state,
        String zip,
        String country) {
}
