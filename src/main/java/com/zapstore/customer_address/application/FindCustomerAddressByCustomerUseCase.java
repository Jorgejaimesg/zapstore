package com.zapstore.customer_address.application;

import java.util.List;

import com.zapstore.customer_address.domain.entity.CustomerAddress;
import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class FindCustomerAddressByCustomerUseCase {
    private final CustomerAddressService customer_addressService;

    public FindCustomerAddressByCustomerUseCase(CustomerAddressService customer_addressService) {
        this.customer_addressService = customer_addressService;
    }

    public List<CustomerAddress> findCustomerAddressByCustomer(String customer_addressName) {
        return customer_addressService.findCustomerAddressByCustomer(customer_addressName);
    }
}
