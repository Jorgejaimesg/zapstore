package com.zapstore.payment_method.application;

import java.util.List;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class FindAllPaymentMethodUseCase {
    private final PaymentMethodService payment_methodService;

    public FindAllPaymentMethodUseCase(PaymentMethodService payment_methodService) {
        this.payment_methodService = payment_methodService;
    }

    public List<PaymentMethod> findAllPaymentMethod() {
        return payment_methodService.findAllPaymentMethod();
    }
}
