package com.example.api.controller;

import com.example.api.service.PurchaseService;
import com.example.domain.Person;
import com.example.api.service.PersonService;
import com.example.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api")
public class RestApi {

    private final PersonService personService;
    private final PurchaseService purchaseService;

    public RestApi(PersonService personService, PurchaseService purchaseService) {
        this.personService = personService;
        this.purchaseService = purchaseService;
    }


    @GetMapping("/people")
    public ResponseEntity<Page<Person>> getAllPeople(@RequestParam() Integer startingPage, @RequestParam(required = false) Integer size) {
         return ResponseEntity.ok(personService.getAllPeople(startingPage, size));
     }

     @PostMapping@GetMapping("/city/people")
     public ResponseEntity<List<Person>> findByCity(@RequestParam() String  city) {
          return ResponseEntity.ok(personService.findByCity(city));
     }


    @GetMapping("/purchases")
    public ResponseEntity<Object> purchase() {
        var body  =  purchaseService.makePurchase(new Purchase(
                "123",
                "123",
                "123",
                12.0,
                "123", new ArrayList<>()));

        return ResponseEntity.ok(body);
    }

}
