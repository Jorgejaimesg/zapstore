package com.zapstore.customer_type.application;

import java.util.List;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class FindAllCustomerTypeUseCase {
    private final CustomerTypeService customer_typeService;

    public FindAllCustomerTypeUseCase(CustomerTypeService customer_typeService) {
        this.customer_typeService = customer_typeService;
    }

    public List<CustomerType> findAllCustomerType() {
        return customer_typeService.findAllCustomerType();
    }
}
