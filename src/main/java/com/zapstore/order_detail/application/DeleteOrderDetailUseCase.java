package com.zapstore.order_detail.application;

import com.zapstore.order_detail.domain.service.OrderDetailService;

public class DeleteOrderDetailUseCase {
    private final OrderDetailService order_detailService;

    public DeleteOrderDetailUseCase(OrderDetailService order_detailService){
        this.order_detailService = order_detailService;
    }

    public void execute(int OrderDetailId){
        order_detailService.deleteOrderDetail(OrderDetailId);
    }
}
