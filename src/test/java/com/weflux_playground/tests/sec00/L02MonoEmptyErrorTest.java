package com.weflux_playground.tests.sec00;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class L02MonoEmptyErrorTest {

    Mono<String> getuserName(int id) {
        return switch (id) {
            case 1 -> Mono.just("user-1");
            case 2 -> Mono.just("user-2");
            case 3 -> Mono.empty();
            default -> Mono.error(new RuntimeException("user not found"));
        };
    }

    @Test
    public void getuserNameTest() {
        // log.info("test started");
        StepVerifier.create(getuserName(1))
                .expectNext("user-1")
                .expectComplete()
                .verify();

    }

    @Test
    public void emptyTest() {
        // log.info("test started");
        StepVerifier.create(getuserName(3))

                .expectComplete()
                .verify();

    }

    @Test
    public void errorTest1() {
        // log.info("test started");
        StepVerifier.create(getuserName(4))
                .expectError()
                .verify();

    }

    @Test
    public void errorTest2() {
        // log.info("test started");
        StepVerifier.create(getuserName(4))
                .expectError(RuntimeException.class)
                .verify();

    }

    @Test
    public void errorTest3() {
        // log.info("test started");
        StepVerifier.create(getuserName(4))

                .expectErrorMessage("user not found")
                .verify();

    }

    @Test
    public void errorTest4() {
        // log.info("test started");
        StepVerifier.create(getuserName(4))
                .consumeErrorWith(e -> {
                    Assertions.assertTrue(e instanceof RuntimeException);
                    Assertions.assertEquals("user not found", e.getMessage());
                })
                .verify();

    }
}
