package com.weflux_playground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L01MonoTest {

    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "product-" + id)
                .doFirst(() -> System.out.println("invoked"));

    }

    @Test
    public void productest() {
        // log.info("test started");
        StepVerifier.create(getProduct(1))
                .expectNext("product-1")
                .expectComplete()
                .verify();

    }

}
