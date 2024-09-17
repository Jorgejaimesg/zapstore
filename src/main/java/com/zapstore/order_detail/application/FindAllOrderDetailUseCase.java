package com.zapstore.order_detail.application;

import java.util.List;

import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;

public class FindAllOrderDetailUseCase {
    private final OrderDetailService order_detailService;

    public FindAllOrderDetailUseCase(OrderDetailService order_detailService) {
        this.order_detailService = order_detailService;
    }

    public List<OrderDetail> findAllOrderDetail() {
        return order_detailService.findAllOrderDetail();
    }
}
