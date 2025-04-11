package com.weflux_playground.tests.sec00;

import static org.mockito.Mockito.lenient;

import java.util.function.UnaryOperator;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class L09PublisherTest {

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux.filter(e -> e.length() > 1)
                .map(e -> e.toUpperCase())
                .map(e -> e + ":" + e.length());
    }

    @Test
    public void publishertest() {

        var publisher = TestPublisher.<String>create();

        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "hello"))
                .expectNext("HI:2", "HELLO:5")
                .expectComplete()
                .verify();

    }
}
