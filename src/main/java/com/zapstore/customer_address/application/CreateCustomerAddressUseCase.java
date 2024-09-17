package com.zapstore.customer_address.application;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class CreateCustomerAddressUseCase {
    private final CustomerAddressService customer_addressService;

    public CreateCustomerAddressUseCase(CustomerAddressService customer_addressService){
        this.customer_addressService = customer_addressService;
    }

    public void execute(CustomerAddress CustomerAddress){
        customer_addressService.createCustomerAddress(CustomerAddress);
    }
}
