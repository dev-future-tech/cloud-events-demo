package com.example.cloud.addressmanagement;

public record AddressDto(
        String street,
        String city,
        String state,
        String zip,
        String country) {
}
