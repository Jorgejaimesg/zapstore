package com.zapstore.customer.application;

import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;

public class CreateCustomerUseCase {
    private final CustomerService customerService;

    public CreateCustomerUseCase(CustomerService customerService){
        this.customerService = customerService;
    }

    public void execute(Customer Customer){
        customerService.createCustomer(Customer);
    }
}
