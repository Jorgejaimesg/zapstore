package com.zapstore.payment_method.application;

import java.util.Optional;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class FindPaymentMethodByNameUseCase {
    private final PaymentMethodService payment_methodService;

    public FindPaymentMethodByNameUseCase(PaymentMethodService payment_methodService) {
        this.payment_methodService = payment_methodService;
    }

    public Optional<PaymentMethod> findPaymentMethodByName(String payment_methodName) {
        return payment_methodService.findPaymentMethodByName(payment_methodName);
    }
}
