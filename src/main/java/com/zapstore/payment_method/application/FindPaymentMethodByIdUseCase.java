package com.zapstore.payment_method.application;

import java.util.Optional;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class FindPaymentMethodByIdUseCase {
    private final PaymentMethodService payment_methodService;
    public FindPaymentMethodByIdUseCase(PaymentMethodService payment_methodService) {
        this.payment_methodService = payment_methodService;
    }

    public Optional<PaymentMethod> findPaymentMethodById(int id) {
        return payment_methodService.findPaymentMethodById(id);
    }
}
