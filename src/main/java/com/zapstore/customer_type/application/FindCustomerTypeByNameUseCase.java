package com.zapstore.customer_type.application;

import java.util.Optional;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class FindCustomerTypeByNameUseCase {
    private final CustomerTypeService customer_typeService;

    public FindCustomerTypeByNameUseCase(CustomerTypeService customer_typeService) {
        this.customer_typeService = customer_typeService;
    }

    public Optional<CustomerType> findCustomerTypeByName(String customer_typeName) {
        return customer_typeService.findCustomerTypeByName(customer_typeName);
    }
}
