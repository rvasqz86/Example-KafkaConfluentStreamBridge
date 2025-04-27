package com.example.api.config;

import com.example.customer.PersonToCustomerKafka;
import com.example.customer.PersonToCustomerRest;
import com.example.tranformers.BaseTransformer;
import com.example.api.data.PersonRepository;
import com.example.api.data.PurchaseRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
public class TransformConfig {

    @Bean(name = "transformers")
    List<BaseTransformer<?,?>> baseTransformList(PersonRepository basePersonRepository, PurchaseRepository basePurchaseRepository ){
        var restTransform = new PersonToCustomerRest(basePersonRepository, basePurchaseRepository);
        List<BaseTransformer<?,?>> baseTransformList = new ArrayList<>();
        baseTransformList.add(new PersonToCustomerKafka(basePersonRepository, basePurchaseRepository));
        baseTransformList.add(new PersonToCustomerRest(basePersonRepository, basePurchaseRepository));
        baseTransformList.add(restTransform);
        return baseTransformList;
    }

}
