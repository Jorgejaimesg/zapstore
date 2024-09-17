package com.zapstore.status_order.application;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class CreateStatusOrderUseCase {
    private final StatusOrderService status_orderService;

    public CreateStatusOrderUseCase(StatusOrderService status_orderService){
        this.status_orderService = status_orderService;
    }

    public void execute(StatusOrder StatusOrder){
        status_orderService.createStatusOrder(StatusOrder);
    }
}
