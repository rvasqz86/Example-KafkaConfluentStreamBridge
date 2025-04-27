package com.example.customer;

import com.example.Customer;
import com.example.domain.Person;
import com.example.domain.Purchase;
import com.example.repository.BasePersonRepository;
import com.example.repository.BasePurchaseRepository;
import com.example.tranformers.KafkaTransform;
import com.example.tranformers.OnPurchaseTransform;
import com.example.tranformers.TransformerType;

import java.util.List;

public class PersonToCustomerKafka implements OnPurchaseTransform<Customer>, KafkaTransform {
    final BasePersonRepository basePersonRepository;
    final BasePurchaseRepository basePurchaseRepository;

    //This one will not be available in the constructor via autowiring so we will use a default constructor
    final PurchaseToCustomerMapper purchaseToCustomerMapper = new PurchaseToCustomerMapper();

    public PersonToCustomerKafka(BasePersonRepository basePersonRepository, BasePurchaseRepository basePurchaseRepository) {
        this.basePersonRepository = basePersonRepository;
        this.basePurchaseRepository = basePurchaseRepository;
    }

    @Override
    public Customer apply(Purchase purchase) {
        String email = purchase.email();

        Person person = basePersonRepository.findByEmail(email).orElse(null);
        if(person == null) {
            return null;
        }
        List<Purchase> purchases = basePurchaseRepository.findByEmail(email);

        return purchaseToCustomerMapper.apply(person, purchases);
    }

    @Override
    public TransformerType getTransformerType() {
        return TransformerType.KAKFA;
    }

    @Override
    public String getTopicName() {
        return "customer-analytic-person";
    }

    @Override
    public List<String> getKey() {
        return List.of("id");
    }

    @Override
    public String getNameSpace() {
        return "customer-analytic-person";
    }
}
