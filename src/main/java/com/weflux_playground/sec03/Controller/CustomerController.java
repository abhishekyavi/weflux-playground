package com.weflux_playground.sec03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.http.ResponseEntity;
=======
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.RequestParam;
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    @GetMapping("paginated")
    public Flux<CustomerDto> getAllCustomers(@RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        return customerService.getAllCustomesr(page, size);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<CustomerDto>> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseEntity.ok(customer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
=======
    @GetMapping("{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
>>>>>>> Stashed changes
    }

    @PostMapping
    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping("{id}")
<<<<<<< Updated upstream
    public Mono<ResponseEntity<CustomerDto>> updateCustomer(@PathVariable Integer id,
            @RequestBody Mono<CustomerDto> customerDto) {
        return customerService.updateCustomer(id, customerDto).map(c -> ResponseEntity.ok(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Integer id) {
        return this.customerService.deleteCustomer(id)
                .filter(b -> b)
                .map(b -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

=======
    public Mono<CustomerDto> updateCustomer(@PathVariable Integer id, @RequestBody Mono<CustomerDto> customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }
>>>>>>> Stashed changes
}
