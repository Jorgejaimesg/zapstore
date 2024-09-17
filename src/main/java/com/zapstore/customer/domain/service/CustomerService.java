package com.zapstore.customer.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.customer.domain.entity.Customer;

public interface CustomerService {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(String customerId);
    Optional<Customer> findCustomerById(String customerId);
    List<Customer> findAllCustomer();
}
