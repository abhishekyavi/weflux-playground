package com.weflux_playground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L04RangeTest {

    private Flux<Integer> getNumbers() {
        return Flux.range(1, 50);
        // .log();

    }

    private Flux<Integer> getRandomNumbers() {
        return Flux.range(1, 50)
                .map(i -> i + (int) (Math.random() * 10));
        // .log();

    }

    @Test
    public void rangeTes1() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1, 2, 3)
                .expectNextCount(47)
                .expectComplete()
                .verify();

    }

    @Test
    public void rangeTes2() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1, 2, 3)
                .expectNextCount(22)
                .expectNext(26, 27, 28)
                .expectNextCount(22)
                .expectComplete()
                .verify();

    }

    @Test
    public void rangeTes3() {
        // log.info("test started");
        StepVerifier.create(getRandomNumbers())
                .expectNextMatches(i -> i > 0 && i < 500)
                .expectNextCount(49)

                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTes4() {
        // log.info("test started");
        StepVerifier.create(getRandomNumbers())
                .thenConsumeWhile(i -> i > 0 && i < 500)
                .expectComplete()
                .verify();
    }

}
