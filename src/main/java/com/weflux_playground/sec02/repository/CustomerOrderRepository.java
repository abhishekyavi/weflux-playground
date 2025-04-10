package com.weflux_playground.sec02.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.weflux_playground.sec02.dto.OrderDetails;
import com.weflux_playground.sec02.entity.CustomerOrder;
import com.weflux_playground.sec02.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface CustomerOrderRepository extends ReactiveCrudRepository<CustomerOrder, UUID> {
    // Custom query methods can be defined here if needed
    // Flux<CustomerOrder> findByCustomerId(Integer customerId);

    @Query("""
             SELECT
                p.*
            FROM
                customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON co.product_id = p.id
            WHERE
                c.name = :name
                      """)
    Flux<Product> getProductsOrderedByCustomer(String name); // This method is not used in the test, but it's
                                                             // here for reference

    @Query("""
            	SELECT
                co.order_id,
                c.name AS customer_name,
                p.description AS product_name,
                co.amount,
                co.order_date
            FROM
                customer c
            INNER JOIN customer_order co ON c.id = co.customer_id
            INNER JOIN product p ON p.id = co.product_id
            WHERE
                p.description = :description
            ORDER BY co.amount DESC
                     """)
    Flux<OrderDetails> getOrderDetailsByProduct(String description);

}
