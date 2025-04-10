package com.weflux_playground.sec02.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.weflux_playground.sec02.entity.Customer;

import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    // Custom query methods can be defined here if needed

    Flux<Customer> findByName(String name);

    Flux<Customer> findByNameContaining(String name);

}
