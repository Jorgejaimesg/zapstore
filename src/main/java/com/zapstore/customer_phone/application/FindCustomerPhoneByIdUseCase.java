package com.zapstore.customer_phone.application;

import java.util.Optional;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class FindCustomerPhoneByIdUseCase {
    private final CustomerPhoneService customer_phoneService;
    public FindCustomerPhoneByIdUseCase(CustomerPhoneService customer_phoneService) {
        this.customer_phoneService = customer_phoneService;
    }

    public Optional<CustomerPhone> findCustomerPhoneById(int id) {
        return customer_phoneService.findCustomerPhoneById(id);
    }
}
