package com.weflux_playground.sec02.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.weflux_playground.sec02.entity.Product;

import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
    // Custom query methods can be defined here if needed
Flux<Product> findByPriceBetween(int from , int to);

Flux<Product> findBy(Pageable pageable); // This method is not used in the test, but it's here for reference





}
