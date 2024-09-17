package com.zapstore.payment_method.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.payment_method.domain.entity.PaymentMethod;

public interface PaymentMethodService {
    void createPaymentMethod(PaymentMethod PaymentMethod);
    void updatePaymentMethod(PaymentMethod PaymentMethod);
    PaymentMethod deletePaymentMethod(String name);
    Optional<PaymentMethod> findPaymentMethodByName (String name);
    Optional<PaymentMethod> findPaymentMethodById (int Id);
    List<PaymentMethod> findAllPaymentMethod();
}
