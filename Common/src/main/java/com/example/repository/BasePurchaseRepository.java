package com.example.repository;

import com.example.domain.Purchase;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BasePurchaseRepository extends PagingAndSortingRepository<Purchase, String> {
    List<Purchase> findByEmail(String email);
}
