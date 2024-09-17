package com.zapstore.customer_type.application;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class CreateCustomerTypeUseCase {
    private final CustomerTypeService customer_typeService;

    public CreateCustomerTypeUseCase(CustomerTypeService customer_typeService){
        this.customer_typeService = customer_typeService;
    }

    public void execute(CustomerType CustomerType){
        customer_typeService.createCustomerType(CustomerType);
    }
}
