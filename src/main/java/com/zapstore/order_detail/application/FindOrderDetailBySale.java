package com.zapstore.order_detail.application;

import java.util.List;

import com.zapstore.order_detail.domain.entity.OrderDetail;
import com.zapstore.order_detail.domain.service.OrderDetailService;

public class FindOrderDetailBySale {
    private final OrderDetailService order_detailService;

    public FindOrderDetailBySale(OrderDetailService order_detailService) {
        this.order_detailService = order_detailService;
    }

    public List<OrderDetail> findOrderDetailBySale(int SaleId) {
        return order_detailService.findOrderDetailBySale(SaleId);
    }
}
