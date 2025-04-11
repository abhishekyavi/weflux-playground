package com.weflux_playground.sec04.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weflux_playground.sec04.dto.CustomerDto;
import com.weflux_playground.sec04.exceptions.ApplicationExceptions;
import com.weflux_playground.sec04.service.CustomerService;
import com.weflux_playground.sec04.validator.RequestValidator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomesr();
    }

    @GetMapping("paginated")
    public Flux<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        return customerService.getAllCustomesr(page, size);
    }

    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .switchIfEmpty(ApplicationExceptions.customerNotFoundExceptions(id));

    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> mono) {
        // var validatedmono= mono.transform(RequestValidator.validate());
        // return customerService.saveCustomer(validatedmono);
        return mono.transform(RequestValidator.validate())
                .as(this.customerService::saveCustomer);

    }

    @PutMapping("{id}")
    public Mono<CustomerDto> updateCustomer(@PathVariable Integer id,
            @RequestBody Mono<CustomerDto> mono) {
        return mono.transform(RequestValidator.validate())
                .as(validreq -> this.customerService.updateCustomer(id, validreq))
                .switchIfEmpty(ApplicationExceptions.customerNotFoundExceptions(id));

    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return this.customerService.deleteCustomer(id)
                .filter(b -> b)
                .switchIfEmpty(ApplicationExceptions.customerNotFoundExceptions(id))
                .then();

    }

}
