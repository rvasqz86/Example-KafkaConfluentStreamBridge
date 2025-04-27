package com.example.repository;

import com.example.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseProductRepository extends PagingAndSortingRepository<Product, String> {
}
