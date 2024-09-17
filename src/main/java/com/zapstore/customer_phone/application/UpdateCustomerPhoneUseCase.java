package com.zapstore.customer_phone.application;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class UpdateCustomerPhoneUseCase {
    private final CustomerPhoneService customer_phoneService;

    public UpdateCustomerPhoneUseCase(CustomerPhoneService customer_phoneService){
        this.customer_phoneService = customer_phoneService;
    }

    public void execute(CustomerPhone customer_phone){
        customer_phoneService.updateCustomerPhone(customer_phone);
    }
}
