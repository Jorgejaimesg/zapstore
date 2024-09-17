package com.zapstore.payment_method.application;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class CreatePaymentMethodUseCase {
    private final PaymentMethodService payment_methodService;

    public CreatePaymentMethodUseCase(PaymentMethodService payment_methodService){
        this.payment_methodService = payment_methodService;
    }

    public void execute(PaymentMethod PaymentMethod){
        payment_methodService.createPaymentMethod(PaymentMethod);
    }
}
