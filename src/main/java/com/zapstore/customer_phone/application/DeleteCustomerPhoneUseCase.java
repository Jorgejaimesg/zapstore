package com.zapstore.customer_phone.application;

import com.zapstore.customer_phone.domain.entity.CustomerPhone;
import com.zapstore.customer_phone.domain.service.CustomerPhoneService;

public class DeleteCustomerPhoneUseCase {
    private final CustomerPhoneService customer_phoneService;

    public DeleteCustomerPhoneUseCase (CustomerPhoneService customer_phoneService) {
        this.customer_phoneService = customer_phoneService;
    }

    public CustomerPhone execute(int id) {
        return customer_phoneService.deleteCustomerPhone(id);
    }
}
