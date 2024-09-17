package com.zapstore.customer_phone.application;

import java.util.List;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class FindCustomerPhoneByCustomerUseCase {
    private final CustomerPhoneService customer_phoneService;

    public FindCustomerPhoneByCustomerUseCase(CustomerPhoneService customer_phoneService) {
        this.customer_phoneService = customer_phoneService;
    }

    public List<CustomerPhone> findCustomerPhoneByCustomer(String customer_phoneName) {
        return customer_phoneService.findCustomerPhoneByCustomer(customer_phoneName);
    }
}
