package com.weflux_playground.tests.sec00;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L10TimeOutTest {

    private Flux<Integer> getNumbers() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
        // .log();

    }

    @Test
    public void TimeOutTest() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1, 2, 3, 4, 5)

                .expectComplete()
                .verify(Duration.ofMillis(1500));

    }

}
