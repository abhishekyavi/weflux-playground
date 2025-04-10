package com.weflux_playground.sec03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weflux_playground.sec03.dto.CustomerDto;
import com.weflux_playground.sec03.service.CustomerService;

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

    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping("{id}")
    public Mono<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody Mono<CustomerDto> customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }
}
