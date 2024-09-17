package com.zapstore.customer.application;

import java.util.List;

import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;

public class FindAllCustomerUseCase {
    private final CustomerService customerService;

    public FindAllCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> findAllCustomer() {
        return customerService.findAllCustomer();
    }
}
