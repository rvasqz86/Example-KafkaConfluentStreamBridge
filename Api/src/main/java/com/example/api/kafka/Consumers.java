package com.example.api.kafka;

import com.example.api.service.PersonService;
import com.example.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class Consumers {
    private final PersonService personService;

    public Consumers(PersonService personService) {
        this.personService = personService;
    }

    public void listen(Person person) {
        System.out.println("Received Message in group fooGroup: " + person);
        if (person.id() == null) {
            throw new RuntimeException("failed");
        }
        personService.update(person);
    }
}
