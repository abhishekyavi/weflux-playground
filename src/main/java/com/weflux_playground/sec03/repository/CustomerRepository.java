package com.weflux_playground.sec03.repository;

<<<<<<< Updated upstream
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
=======
>>>>>>> Stashed changes
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.weflux_playground.sec03.entity.Customer;

<<<<<<< Updated upstream
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

=======
>>>>>>> Stashed changes
@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
    // Custom query methods can be defined here if needed

<<<<<<< Updated upstream
    @Modifying
    @Query("Delete  FROM customer WHERE id = :id")
    Mono<Boolean> deleteCustomerById(Integer id); // Custom delete method to return Mono<Boolean>

    Flux<Customer> findBy(Pageable pageable); // Custom method to find customers with pagination{

=======
>>>>>>> Stashed changes
}
