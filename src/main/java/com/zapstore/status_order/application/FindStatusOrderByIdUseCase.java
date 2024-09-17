package com.zapstore.status_order.application;

import java.util.Optional;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class FindStatusOrderByIdUseCase {
    private final StatusOrderService status_orderService;
    public FindStatusOrderByIdUseCase(StatusOrderService status_orderService) {
        this.status_orderService = status_orderService;
    }

    public Optional<StatusOrder> findStatusOrderById(int id) {
        return status_orderService.findStatusOrderById(id);
    }
}
