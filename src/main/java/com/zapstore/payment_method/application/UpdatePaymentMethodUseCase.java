package com.zapstore.payment_method.application;

import com.zapstore.payment_method.domain.entity.PaymentMethod;
import com.zapstore.payment_method.domain.service.PaymentMethodService;

public class UpdatePaymentMethodUseCase {
    private final PaymentMethodService payment_methodService;

    public UpdatePaymentMethodUseCase(PaymentMethodService payment_methodService){
        this.payment_methodService = payment_methodService;
    }

    public void execute(PaymentMethod payment_method){
        payment_methodService.updatePaymentMethod(payment_method);
    }
}
