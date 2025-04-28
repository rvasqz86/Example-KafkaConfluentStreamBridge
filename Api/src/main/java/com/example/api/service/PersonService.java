package com.example.api.service;

import com.example.api.data.PersonRepository;
import com.example.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    final PersonRepository basePersonRepository;

    public PersonService(PersonRepository basePersonRepository) {
        this.basePersonRepository = basePersonRepository;
    }

    public Page<Person> getAllPeople(Integer startingPage, Integer size) {
        startingPage = Optional.ofNullable(startingPage).orElse(0);
        size = Optional.ofNullable(size).orElse(10);
        Pageable pageable = PageRequest.of(startingPage, size);
        return basePersonRepository.findAll(pageable);
    }

    public List<Person> findByCity(String city) {
        return basePersonRepository.findByAddress_City(city);
    }

    public void update(Person person) {
        System.out.println("updating person: " + person);
    }
}
