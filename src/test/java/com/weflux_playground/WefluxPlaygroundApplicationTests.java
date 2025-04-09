package com.weflux_playground;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class WefluxPlaygroundApplicationTests {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(WefluxPlaygroundApplicationTests.class);

	@Test
	void contextLoads() {
	}
	// private static Logger log =
	// org.slf4j.LoggerFactory.getLogger(L01MonoTest.class);

	private Mono<String> getProduct(int id) {
		return Mono.fromSupplier(() -> "product-" + id)
				.doFirst(() -> log.info("invoked"));

	}

	@Test
	public void productest() {
		log.info("test started");
		StepVerifier.create(getProduct(1))
				.expectNext("product-1")
				.expectComplete()
				.verify();

	}

}
