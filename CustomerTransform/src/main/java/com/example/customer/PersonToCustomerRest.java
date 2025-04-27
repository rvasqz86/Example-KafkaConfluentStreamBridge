package com.example.customer;

import com.example.Customer;
import com.example.domain.NonAvroCustomer;
import com.example.domain.Person;
import com.example.domain.Purchase;
import com.example.repository.BasePersonRepository;
import com.example.repository.BasePurchaseRepository;
import com.example.tranformers.OnPurchaseTransform;
import com.example.tranformers.RestTransform;
import com.example.tranformers.TransformerType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

import java.time.ZonedDateTime;
import java.util.List;

public class PersonToCustomerRest implements OnPurchaseTransform<NonAvroCustomer>, RestTransform {
    static RestClient restClient = null;
    final BasePersonRepository basePersonRepository;
    final BasePurchaseRepository basePurchaseRepository;

    //This one will not be available in the constructor via autowiring so we will use a default constructor
    final PurchaseToCustomerMapper purchaseToCustomerMapper = new PurchaseToCustomerMapper();


    private OAuth2ClientHttpRequestInterceptor interceptor;

    public PersonToCustomerRest(BasePersonRepository basePersonRepository, BasePurchaseRepository basePurchaseRepository) {
        this.basePersonRepository = basePersonRepository;
        this.basePurchaseRepository = basePurchaseRepository;
    }

    @Override
    public NonAvroCustomer apply(Purchase purchase) {
        Person person = basePersonRepository.findByEmail(purchase.email()).orElse(null);
        if(person == null) {
            return null;
        }
        List<Purchase> purchases = basePurchaseRepository.findByEmail(purchase.email());
        return new NonAvroCustomer(
                person.id(),
                person.biography(),
                person.biography(),
                purchases.stream().mapToDouble(Purchase::price).sum(),
                ZonedDateTime.now(),
                purchases);
    }



    @Override
    public TransformerType getTransformerType() {
        return TransformerType.REST;
    }

    @Override
    public String getPath() {
        return "/send_receipt_email";
    }

    //We will set this in spring
    @Override
    public void setOAuth2ClientHttpRequestInterceptor(OAuth2ClientHttpRequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public RestClient getRestClient() {
        if(restClient != null) {
            return restClient;
        }
        restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .baseUrl("https://example.com")
                .requestInterceptor(interceptor)
                .build();
        return restClient;
    }

    @Override
    public OAuth2ClientHttpRequestInterceptor getOAuth2ClientHttpRequestInterceptor() {
        return interceptor;
    }
}
