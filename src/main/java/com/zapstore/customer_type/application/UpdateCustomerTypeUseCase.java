package com.zapstore.customer_type.application;

import com.zapstore.customer_type.domain.entity.CustomerType;
import com.zapstore.customer_type.domain.service.CustomerTypeService;

public class UpdateCustomerTypeUseCase {
    private final CustomerTypeService customer_typeService;

    public UpdateCustomerTypeUseCase(CustomerTypeService customer_typeService){
        this.customer_typeService = customer_typeService;
    }

    public void execute(CustomerType customer_type){
        customer_typeService.updateCustomerType(customer_type);
    }
}
