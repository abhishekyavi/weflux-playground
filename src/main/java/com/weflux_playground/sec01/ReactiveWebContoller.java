package com.weflux_playground.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("reactive")
public class ReactiveWebContoller {
    private static final Logger log = LoggerFactory.getLogger(ReactiveWebContoller.class);

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:7070")
            .build();

    @GetMapping("/products")
    public Flux<Product> getProducts() {
        return webClient.get()
                .uri("/demo01/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext(product -> log.info("getProducts() called, response: {}", product))
                .doOnError(error -> log.error("Error occurred while fetching products: {}", error.getMessage()));

    }

    @GetMapping(value = "/products/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getProductsStream() {
        return webClient.get()
                .uri("/demo01/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext(product -> log.info("getProductsStream() called, response: {}", product))
                .doOnError(error -> log.error("Error occurred while fetching products stream: {}", error.getMessage()));
    }

}
