package com.example.api.service;

import com.example.api.annotation.PublishOnReturn;
import com.example.domain.Purchase;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @PublishOnReturn
    public Purchase makePurchase(Purchase purchase) {
        return purchase;
    }
}
