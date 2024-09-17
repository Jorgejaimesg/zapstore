package com.zapstore.customer_type.application;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class DeleteCustomerTypeUseCase {
    private final CustomerTypeService customer_typeService;

    public DeleteCustomerTypeUseCase (CustomerTypeService customer_typeService) {
        this.customer_typeService = customer_typeService;
    }

    public CustomerType execute(String Name) {
        return customer_typeService.deleteCustomerType(Name);
    }
}
