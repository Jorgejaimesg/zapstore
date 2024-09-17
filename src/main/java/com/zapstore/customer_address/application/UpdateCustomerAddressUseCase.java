package com.zapstore.customer_address.application;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class UpdateCustomerAddressUseCase {
    private final CustomerAddressService customer_addressService;

    public UpdateCustomerAddressUseCase(CustomerAddressService customer_addressService){
        this.customer_addressService = customer_addressService;
    }

    public void execute(CustomerAddress customer_address){
        customer_addressService.updateCustomerAddress(customer_address);
    }
}
