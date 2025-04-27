package com.example.api.service;

import com.example.api.annotation.PublishOnReturn;
import com.example.domain.Purchase;
import com.example.api.kafka.Publisher;
import com.example.tranformers.*;
import com.example.api.data.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @PublishOnReturn
    public Purchase makePurchase(Purchase purchase) {
        return purchase;
    }
}
