package com.zapstore.status_order.application;

import java.util.Optional;

import com.zapstore.status_order.domain.entity.StatusOrder;
import com.zapstore.status_order.domain.service.StatusOrderService;

public class FindStatusOrderByNameUseCase {
    private final StatusOrderService status_orderService;

    public FindStatusOrderByNameUseCase(StatusOrderService status_orderService) {
        this.status_orderService = status_orderService;
    }

    public Optional<StatusOrder> findStatusOrderByName(String status_orderName) {
        return status_orderService.findStatusOrderByName(status_orderName);
    }
}
