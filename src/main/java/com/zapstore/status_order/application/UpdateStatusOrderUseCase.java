package com.zapstore.status_order.application;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class UpdateStatusOrderUseCase {
    private final StatusOrderService status_orderService;

    public UpdateStatusOrderUseCase(StatusOrderService status_orderService){
        this.status_orderService = status_orderService;
    }

    public void execute(StatusOrder status_order){
        status_orderService.updateStatusOrder(status_order);
    }
}
