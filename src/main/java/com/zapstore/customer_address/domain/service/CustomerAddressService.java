

package com.zapstore.customer_address.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.customer_address.domain.entity.CustomerAddress;

public interface CustomerAddressService {
    void createCustomerAddress(CustomerAddress customer_address);
    void updateCustomerAddress(CustomerAddress customerAddressId);
    void deleteCustomerAddress(int customerAddressId);
    Optional<CustomerAddress> findCustomerAddressById(int customerAddressId);
    List<CustomerAddress> findCustomerAddressByCustomer(String customerId);
    List<CustomerAddress> findAllCustomerAddress();
}
