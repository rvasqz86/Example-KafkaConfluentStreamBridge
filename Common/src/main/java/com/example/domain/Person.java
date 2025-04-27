package com.example.domain;

public record Person(
    String id,
    String name,
    String email,
    String biography,
    String employmentStatus,
    Address address,
    String createdAt,
    String updatedAt
){};
