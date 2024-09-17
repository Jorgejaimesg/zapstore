package com.zapstore.customer.application;

import java.util.Optional;

import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;

public class FindCustomerByIdUseCase {
    private final CustomerService customerService;

    public FindCustomerByIdUseCase(CustomerService customerService){
        this.customerService = customerService;
    }

    public Optional<Customer> findCustomerById(String id){
        return customerService.findCustomerById(id);
    }
}
