package com.zapstore.payment_method.application;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class DeletePaymentMethodUseCase {
    private final PaymentMethodService payment_methodService;

    public DeletePaymentMethodUseCase (PaymentMethodService payment_methodService) {
        this.payment_methodService = payment_methodService;
    }

    public PaymentMethod execute(String Name) {
        return payment_methodService.deletePaymentMethod(Name);
    }
}
