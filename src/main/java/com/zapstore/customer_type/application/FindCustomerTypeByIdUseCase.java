package com.zapstore.customer_type.application;

import java.util.Optional;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class FindCustomerTypeByIdUseCase {
    private final CustomerTypeService customer_typeService;
    public FindCustomerTypeByIdUseCase(CustomerTypeService customer_typeService) {
        this.customer_typeService = customer_typeService;
    }

    public Optional<CustomerType> findCustomerTypeById(int id) {
        return customer_typeService.findCustomerTypeById(id);
    }
}
