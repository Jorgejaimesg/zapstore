package com.zapstore.customer.application;

import com.zapstore.customer.domain.service.CustomerService;

public class DeleteCustomerUseCase {
    private final CustomerService customerService;

    public DeleteCustomerUseCase(CustomerService customerService){
        this.customerService = customerService;
    }

    public void execute(String CustomerId){
        customerService.deleteCustomer(CustomerId);
    }
}
