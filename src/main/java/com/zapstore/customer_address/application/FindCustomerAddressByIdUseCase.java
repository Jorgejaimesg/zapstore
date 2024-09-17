package com.zapstore.customer_address.application;

import java.util.Optional;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class FindCustomerAddressByIdUseCase {
    private final CustomerAddressService customer_addressService;
    public FindCustomerAddressByIdUseCase(CustomerAddressService customer_addressService) {
        this.customer_addressService = customer_addressService;
    }

    public Optional<CustomerAddress> findCustomerAddressById(int id) {
        return customer_addressService.findCustomerAddressById(id);
    }
}
