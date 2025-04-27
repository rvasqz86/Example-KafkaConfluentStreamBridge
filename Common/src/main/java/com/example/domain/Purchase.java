package com.example.domain;

import java.util.List;

public record Purchase (String id, String name, String description, double price, String email, List<Product> products) {}
