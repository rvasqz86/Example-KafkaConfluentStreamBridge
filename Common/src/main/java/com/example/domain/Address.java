package com.example.domain;

import java.time.ZonedDateTime;

public record Address(
    String id,
    String street,
    String city,
    String state,
    String country,
    String postalCode,
    ZonedDateTime createdAt,
    ZonedDateTime updatedAt
){ }
