package com.zapstore.customer_phone.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.customer_phone.domain.entity.CustomerPhone;

public interface CustomerPhoneService {
    void createCustomerPhone(CustomerPhone CustomerPhone);
    void updateCustomerPhone(CustomerPhone CustomerPhone);
    CustomerPhone deleteCustomerPhone(int id);
    List<CustomerPhone> findCustomerPhoneByCustomer (String customerid);
    Optional<CustomerPhone> findCustomerPhoneById (int id);
    List<CustomerPhone> findAllCustomerPhone();
}
