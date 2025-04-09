package com.weflux_playground;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class L07ScenarionNameTest {

    private Flux<Integer> getNumbers() {
        return Flux.range(1, 3);
        // .log();

    }

    @Test
    public void ScenarioNameTest1() {
        var options = StepVerifierOptions.create().scenarioName("1 to 3 iteams test");

        StepVerifier.create(getNumbers(), options)
                .expectNext(11)
                .as("first item should be 11")
                .expectNext(2, 3)
                .as("then 2 and 3 should be there")
                .expectComplete()
                .verify();

    }

}
