package com.zapstore.customer_type.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.customer_type.domain.entity.CustomerType;

public interface CustomerTypeService {
    void createCustomerType(CustomerType CustomerType);
    void updateCustomerType(CustomerType CustomerType);
    CustomerType deleteCustomerType(String name);
    Optional<CustomerType> findCustomerTypeByName (String name);
    Optional<CustomerType> findCustomerTypeById (int Id);
    List<CustomerType> findAllCustomerType();
}
