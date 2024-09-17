package com.zapstore.customer.application;

import com.zapstore.customer.domain.entity.Customer;
import com.zapstore.customer.domain.service.CustomerService;

public class UpdateCustomerUseCase {
    private final CustomerService customerService;

    public UpdateCustomerUseCase(CustomerService customerService){
        this.customerService = customerService;
    }

    public void execute(Customer Customer){
        customerService.updateCustomer(Customer);
    }
}
