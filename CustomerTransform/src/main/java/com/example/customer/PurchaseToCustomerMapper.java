package com.example.customer;

import com.example.Customer;
import com.example.domain.Person;
import com.example.domain.Purchase;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PurchaseToCustomerMapper implements BiFunction<Person,List<Purchase>, Customer> {

    @Override
    public Customer apply(Person person, List<Purchase> purchases) {
        Set<ZonedDateTime> purchaseDates = purchases.stream()
                .map(a->ZonedDateTime.now())
                .collect(Collectors.toSet());
        String description = "New customer";
        if (purchaseDates.size() > 1) {
            description = "Loyal customer";
        } else if (purchases.size() > 5) {
            description = "Frequent customer";
        }
        return new Customer(
                person.id(),
                person.name(),
                description,
                purchases.stream().mapToDouble(Purchase::price).sum(),
                1L,
                purchases.stream().map(p-> {
                    com.example.Purchase purchase = new com.example.Purchase();
                    purchase.setId(p.id());
                    purchase.setName(p.name());
                    purchase.setPrice(p.price());
                    return purchase;
                }).collect(Collectors.toList())
        );
    }
}
