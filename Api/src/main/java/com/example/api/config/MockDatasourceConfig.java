package com.example.api.config;

import com.example.api.data.PersonRepository;
import com.example.api.data.PurchaseRepository;
import com.example.domain.Person;
import com.example.domain.Purchase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Configuration
@Profile("mock")
public class MockDatasourceConfig {

    @Bean //fot test, with spring data excluded
    public PersonRepository personRepository() {
        return new PersonRepository() {
            @Override
            public List<Person> findByAddress_City(String city) {
                return List.of();
            }

            @Override
            public Optional<Person> findByEmail(String email) {
                return Optional.of(new Person(
                        "123",
                        "123",
                        "123",
                        "",
                        "123", null, null, null));
            }

            @Override
            public Iterable<Person> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Person> findAll(Pageable pageable) {
                return null;
            }
        };
    }

    @Bean //for testing. with spring data excluded
    public PurchaseRepository purchaseRepository() {
        return new PurchaseRepository() {
            @Override
            public List<Purchase> findByEmail(String email) {
                return List.of();
            }

            @Override
            public Iterable<Purchase> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<Purchase> findAll(Pageable pageable) {
                return null;
            }
        };
    }


}
