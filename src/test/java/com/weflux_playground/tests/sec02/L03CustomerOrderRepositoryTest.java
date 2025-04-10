package com.weflux_playground.tests.sec02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weflux_playground.sec02.repository.CustomerOrderRepository;

import reactor.test.StepVerifier;

public class L03CustomerOrderRepositoryTest extends AbstractTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(L03CustomerOrderRepositoryTest.class);

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Test
    public void productOrderByCustomer() {
        this.customerOrderRepository.getProductsOrderedByCustomer("mike")
                .doOnNext(p -> log.info("Product: {}", p))
                .as(StepVerifier::create)
                .expectNextCount(2)
                .expectComplete()
                .verify();

    }

    @Test
    public void getOrderDetailsByProduct() {
        this.customerOrderRepository.getOrderDetailsByProduct("iphone 20")
                .doOnNext(dto -> log.info("Product: {}", dto))
                .as(StepVerifier::create)
                .assertNext(dto -> Assertions.assertThat(975).isEqualTo(dto.Amount()))
                .assertNext(dto -> Assertions.assertThat(950).isEqualTo(dto.Amount()))
                // .expectNextCount(2)
                .expectComplete()
                .verify();

    }

}
