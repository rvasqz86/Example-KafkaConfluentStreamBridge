package com.example.repository;

import com.example.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface BasePersonRepository extends PagingAndSortingRepository<Person, String> {
    List<Person>  findByAddress_City(String city);
    Optional<Person> findByEmail(String email);
}
