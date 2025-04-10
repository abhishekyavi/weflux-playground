package com.weflux_playground.tests.sec02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.weflux_playground.sec02.repository.ProductRepository;

import reactor.test.StepVerifier;

public class L02ProductRepositoryTest extends AbstractTest {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(L02ProductRepositoryTest.class);

    @Test
    public void findByPriceBetween() {
        productRepository.findByPriceBetween(750, 1000)
                .doOnNext(p -> log.info("Product: {}", p))
                .as(StepVerifier::create)
                .expectNextCount(3) // Adjust the count based on your test data
                .expectComplete()
                .verify();

    }

    @Test
    public void pagebale() {
        productRepository.findBy(PageRequest.of(0, 3).withSort(Sort
                .by("price").ascending()))
                .doOnNext(p -> log.info("Product: {}", p))
                .as(StepVerifier::create)
                // .expectNextCount(3) // Adjust the count based on your test data
                .assertNext(c -> Assertions.assertThat(c.getPrice()).isEqualTo(200))
                .assertNext(c -> Assertions.assertThat(c.getPrice()).isEqualTo(250))
                .assertNext(c -> Assertions.assertThat(c.getPrice()).isEqualTo(300))
                .expectComplete()
                .verify();

    }

}
