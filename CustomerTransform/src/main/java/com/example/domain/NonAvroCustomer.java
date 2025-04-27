package com.example.domain;

import java.time.ZonedDateTime;
import java.util.List;

public record NonAvroCustomer(String id, String name, String description, double totalSpent, ZonedDateTime becameCustomerOn, List<Purchase> purchases) { }
