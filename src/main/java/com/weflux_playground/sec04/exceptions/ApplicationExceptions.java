package com.weflux_playground.sec04.exceptions;

import reactor.core.publisher.Mono;

public class ApplicationExceptions {

    public static <T> Mono<T> customerNotFoundExceptions(Integer id) {
        return Mono.error(new CustomerNotFoundExceptions(id));
    }

    public static <T> Mono<T> missingName() {
        return Mono.error(new InvalidInpuntException("Name is requried"));
    }

    public static <T> Mono<T> missingValidEmail() {
        return Mono.error(new InvalidInpuntException("Valid email is requried"));
    }

}
