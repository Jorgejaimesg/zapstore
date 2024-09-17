package com.zapstore.status_order.application;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class DeleteStatusOrderUseCase {
    private final StatusOrderService status_orderService;

    public DeleteStatusOrderUseCase (StatusOrderService status_orderService) {
        this.status_orderService = status_orderService;
    }

    public StatusOrder execute(String Name) {
        return status_orderService.deleteStatusOrder(Name);
    }
}
