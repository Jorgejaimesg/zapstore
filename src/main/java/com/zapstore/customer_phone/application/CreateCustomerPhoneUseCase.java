package com.zapstore.customer_phone.application;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class CreateCustomerPhoneUseCase {
    private final CustomerPhoneService customer_phoneService;

    public CreateCustomerPhoneUseCase(CustomerPhoneService customer_phoneService){
        this.customer_phoneService = customer_phoneService;
    }

    public void execute(CustomerPhone CustomerPhone){
        customer_phoneService.createCustomerPhone(CustomerPhone);
    }
}
