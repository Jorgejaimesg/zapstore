package com.zapstore.customer_address.application;

import java.util.List;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class FindAllCustomerAddressUseCase {
    private final CustomerAddressService customer_addressService;

    public FindAllCustomerAddressUseCase(CustomerAddressService customer_addressService) {
        this.customer_addressService = customer_addressService;
    }

    public List<CustomerAddress> findAllCustomerAddress() {
        return customer_addressService.findAllCustomerAddress();
    }
}
