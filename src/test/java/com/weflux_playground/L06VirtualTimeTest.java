package com.weflux_playground;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class L06VirtualTimeTest {

    private Flux<Integer> getNumbers() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
        // .log();

    }

    @Test
    public void rangeTes1() {
        // log.info("test started");
        StepVerifier.create(getNumbers())
                .expectNext(1, 2, 3, 4, 5)

                .expectComplete()
                .verify();

    }

    @Test
    public void virtualTimeTest1() {
        StepVerifier.withVirtualTime(this::getNumbers)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();

    }

    @Test
    public void virtualTimeTest2() {
        StepVerifier.withVirtualTime(this::getNumbers)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2, 3, 4, 5)
                .expectComplete()
                .verify();

    }

}
