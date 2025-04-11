package com.weflux_playground.sec04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.weflux_playground.sec04.dto.CustomerDto;
import com.weflux_playground.sec04.mapper.EntityDtoMapper;
import com.weflux_playground.sec04.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Flux<CustomerDto> getAllCustomesr() {

        return this.customerRepository.findAll()
                .map(c -> EntityDtoMapper.toDto(c));

    }

    public Flux<CustomerDto> getAllCustomesr(Integer page, Integer size) {

        return this.customerRepository.findBy(PageRequest.of(page - 1, size))
                .map(c -> EntityDtoMapper.toDto(c));

    }

    public Mono<CustomerDto> getCustomerById(Integer id) {

        return this.customerRepository.findById(id)
                .map(c -> EntityDtoMapper.toDto(c));

    }

    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> mono) {

        return mono.map(c -> EntityDtoMapper.toEntity(c))
                .flatMap(entity -> this.customerRepository.save(entity))
                .map(c -> EntityDtoMapper.toDto(c));

    }

    public Mono<CustomerDto> updateCustomer(Integer id, Mono<CustomerDto> mono) {

        return this.customerRepository.findById(id)
                .flatMap(c -> mono)
                .map(c -> EntityDtoMapper.toEntity(c))
                .doOnNext(c -> c.setId(id))
                .flatMap(this.customerRepository::save)
                .map(c -> EntityDtoMapper.toDto(c));
    }

    public Mono<Boolean> deleteCustomer(Integer id) {

        return this.customerRepository.deleteCustomerById(id);

    }

}
