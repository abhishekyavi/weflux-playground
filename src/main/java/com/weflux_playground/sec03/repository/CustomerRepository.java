package com.weflux_playground.sec03.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.weflux_playground.sec03.entity.Customer;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    // Custom query methods can be defined here if needed

}
