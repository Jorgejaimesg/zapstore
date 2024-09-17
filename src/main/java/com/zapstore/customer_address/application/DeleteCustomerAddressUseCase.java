package com.zapstore.customer_address.application;

import com.zapstore.customer_address.domain.service.CustomerAddressService;

public class DeleteCustomerAddressUseCase {
    private final CustomerAddressService customer_addressService;

    public DeleteCustomerAddressUseCase (CustomerAddressService customer_addressService) {
        this.customer_addressService = customer_addressService;
    }

    public void deleteCustomerAddress (int id) {
        customer_addressService.deleteCustomerAddress(id);
    }
}
