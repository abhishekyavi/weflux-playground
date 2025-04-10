package com.weflux_playground.tests.sec02;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.weflux_playground.sec02.entity.Customer;
import com.weflux_playground.sec02.repository.CustomerRepository;

import reactor.test.StepVerifier;

public class L01RepositoryTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(L01RepositoryTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findall() {
        customerRepository.findAll()
                .doOnNext(c -> log.info("Customer: {}", c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }

    @Test
    public void findbyID() {
        customerRepository.findById(2)
                .doOnNext(c -> log.info("Customer: {}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertThat(c.getId()).isEqualTo(2))

                .expectComplete()
                .verify();
    }

    @Test
    public void findbyName() {
        customerRepository.findByName("mike")
                .doOnNext(c -> log.info("Customer: {}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertThat(c.getName()).isEqualTo("mike"))

                .expectComplete()
                .verify();
    }

    @Test
    public void findbyNameConatining() {
        customerRepository.findByNameContaining("ke")
                .doOnNext(c -> log.info("Customer: {}", c))
                .as(StepVerifier::create)

                // .expectNextCount(2)
                .assertNext(c -> Assertions.assertThat(c.getName()).contains("ke"))
                .assertNext(c -> Assertions.assertThat(c.getName()).contains("ke"))

                .expectComplete()
                .verify();
    }

    @Test
    public void insertAndDeleteCustomer() {
        var customer = new Customer();
        customer.setName("John");
        customer.setEmail("John@gmail.com");
        this.customerRepository.save(customer)
                .doOnNext(c -> log.info("Inserted Customer: {}", c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertThat(c.getName()).isEqualTo("John"))
                .expectComplete()
                .verify();
        // count
        this.customerRepository.count()
                .doOnNext(c -> log.info("Customer Count: {}", c))
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        // delete
        this.customerRepository.deleteById(11)

                .then(this.customerRepository.count())
                .as(StepVerifier::create)
                .expectNextCount(1L)
                .expectComplete()
                .verify();

    }

    // update the customer object

@Test
public void updateCustomer()
{
    this.customerRepository.findByName("ethan")
    .doOnNext(c->c.setName("ram"))
    .flatMap(c->this.customerRepository.save(c))
    .doOnNext(c -> log.info("updated Customer: {}", c))
    .as(StepVerifier::create)
    .assertNext(c -> Assertions.assertThat(c.getName()).isEqualTo("ram"))
    .expectComplete()
    .verify();
    
}
}
