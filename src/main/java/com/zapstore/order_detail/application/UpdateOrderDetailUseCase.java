package com.zapstore.order_detail.application;

import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;

public class UpdateOrderDetailUseCase {
    private final OrderDetailService order_detailService;

    public UpdateOrderDetailUseCase(OrderDetailService order_detailService){
        this.order_detailService = order_detailService;
    }

    public void execute(OrderDetail OrderDetail){
        order_detailService.updateOrderDetail(OrderDetail);
    }
}
