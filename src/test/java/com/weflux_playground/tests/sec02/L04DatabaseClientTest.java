package com.weflux_playground.tests.sec02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;

import com.weflux_playground.sec02.dto.OrderDetails;

import reactor.test.StepVerifier;

public class L04DatabaseClientTest extends AbstractTest {
    private static Logger log = LoggerFactory.getLogger(L04DatabaseClientTest.class);
    @Autowired

    private DatabaseClient client;

    @Test
    public void getOrderDetailsByProduct() {
        var query = """
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
                        """;

        this.client.sql(query)
                .bind("description", "iphone 20")
                .mapProperties(OrderDetails.class)
                .all()
                .doOnNext(dto -> log.info("Product: {}", dto))
                .as(StepVerifier::create)
                .assertNext(dto -> Assertions.assertThat(975).isEqualTo(dto.Amount()))
                .assertNext(dto -> Assertions.assertThat(950).isEqualTo(dto.Amount()))
                .expectComplete()
                .verify();

    }

}
