package com.example.cloudeventsdemo.dto;

public record AddressDto (
    String street,
    String city,
    String state,
    String zip,
    String country){}
