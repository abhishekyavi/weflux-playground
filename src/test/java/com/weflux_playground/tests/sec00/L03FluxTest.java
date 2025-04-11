package com.weflux_playground.tests.sec00;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L03FluxTest {
    private static final Logger log = LoggerFactory.getLogger(L03FluxTest.class);

    private Flux<Integer> getNumbers() {
        return Flux.just(1, 2, 3)
                .log();

    }

    @Test
    public void fluxTes1() {
        // log.info("test started");
        StepVerifier.create(getNumbers(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();

    }

    @Test
    public void fluxTes2() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();

    }

    @Test
    public void fluxTes3() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();

    }

}
