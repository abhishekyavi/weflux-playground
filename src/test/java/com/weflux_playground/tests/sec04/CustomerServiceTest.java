package com.weflux_playground.tests.sec04;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.weflux_playground.sec04.dto.CustomerDto;

@AutoConfigureWebTestClient
@SpringBootTest(properties = { "sec=sec04" })
public class CustomerServiceTest {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceTest.class);

    @Autowired
    private WebTestClient client;

    @Test
    public void getAllCustomers() {
        this.client.get().uri("/customers")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CustomerDto.class)
                .value(list -> {
                    log.info("List of customers: {}", list);
                });

    }

    @Test
    public void getPaginatedCustomers() {
        this.client.get().uri("/customers/paginated?page=3&size=2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().consumeWith(response -> {
                    log.info("Response: {}", new String(response.getResponseBody()));
                })

                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$.[0].id").isEqualTo(5)
                .jsonPath("$.[1].id").isEqualTo(6);

    }

    @Test
    public void getCustomerById() {
        this.client.get().uri("/customers/1")
                .exchange()
                .expectStatus().is2xxSuccessful()

                .expectBody()
                .consumeWith(c -> {
                    log.info("Response: {}", new String(c.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("sam");

    }

    @Test
    public void CreateAndDeleteCustomer() {
        var dto = new CustomerDto(null, "Abhishek", "abhishek@gmail.com");
        this.client.post()
                .uri("/customers")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()

                .expectBody()
                .consumeWith(c -> {
                    log.info("Response: {}", new String(c.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(11)
                .jsonPath("$.name").isEqualTo("Abhishek");
        // delete
        this.client.delete()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().isEmpty();
    }

    @Test
    public void updateCustomer() {
        var dto = new CustomerDto(null, "loin", "lion@gmail.com");
        this.client.put()
                .uri("/customers/10")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()

                .expectBody()
                .consumeWith(c -> {
                    log.info("Response: {}", new String(c.getResponseBody()));
                })
                .jsonPath("$.id").isEqualTo(10)
                .jsonPath("$.name").isEqualTo("loin");
    }

    @Test
    public void CustomerNotFound() {
        // get
        this.client.get().uri("/customers/11")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("Customer[id=11] not found");

        // delete
        this.client.delete()
                .uri("/customers/11")
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("Customer[id=11] not found");

        // put
        var dto = new CustomerDto(null, "loin", "lion@gmail.com");
        this.client.put()
                .uri("/customers/11")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("Customer[id=11] not found");

    }

    @Test
    public void invalidInput() {

        var missingName = new CustomerDto(null, null, "lion@gmail.com");
        this.client.post()
                .uri("/customers")
                .bodyValue(missingName)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("Name is requried");

        // missing email

        var missingEmail = new CustomerDto(null, "abhishek", null);
        this.client.post()
                .uri("/customers")
                .bodyValue(missingEmail)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.detail").isEqualTo("Valid email is requried");

                // inalid emali

                var invalidEmail = new CustomerDto(null, "abhishek", "abhi.com");
                this.client.put()
                        .uri("/customers/10")
                        .bodyValue(invalidEmail)
                        .exchange()
                        .expectStatus().is4xxClientError()
                        .expectBody()
                        .jsonPath("$.detail").isEqualTo("Valid email is requried");

    }

}
