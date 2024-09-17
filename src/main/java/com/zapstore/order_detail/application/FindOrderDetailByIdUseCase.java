package com.zapstore.order_detail.application;

import java.util.Optional;

import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;

public class FindOrderDetailByIdUseCase {
    private final OrderDetailService order_detailService;

    public FindOrderDetailByIdUseCase(OrderDetailService order_detailService){
        this.order_detailService = order_detailService;
    }

    public Optional<OrderDetail> findOrderDetailById(int id){
        return order_detailService.findOrderDetailById(id);
    }
}
