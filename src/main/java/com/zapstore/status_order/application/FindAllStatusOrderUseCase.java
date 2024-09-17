package com.zapstore.status_order.application;

import java.util.List;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class FindAllStatusOrderUseCase {
    private final StatusOrderService status_orderService;

    public FindAllStatusOrderUseCase(StatusOrderService status_orderService) {
        this.status_orderService = status_orderService;
    }

    public List<StatusOrder> findAllStatusOrder() {
        return status_orderService.findAllStatusOrder();
    }
}
