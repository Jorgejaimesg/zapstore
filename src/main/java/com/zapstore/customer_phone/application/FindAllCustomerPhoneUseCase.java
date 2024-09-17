package com.zapstore.customer_phone.application;

import java.util.List;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class FindAllCustomerPhoneUseCase {
    private final CustomerPhoneService customer_phoneService;

    public FindAllCustomerPhoneUseCase(CustomerPhoneService customer_phoneService) {
        this.customer_phoneService = customer_phoneService;
    }

    public List<CustomerPhone> findAllCustomerPhone() {
        return customer_phoneService.findAllCustomerPhone();
    }
}
