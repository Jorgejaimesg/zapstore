package com.zapstore.order_detail.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.order_detail.domain.entity.OrderDetail;

public interface OrderDetailService {
    void createOrderDetail(OrderDetail order_detail);
    void updateOrderDetail(OrderDetail order_detail);
    void deleteOrderDetail(int order_detailId);
    Optional<OrderDetail> findOrderDetailById(int order_detailId);
    List<OrderDetail> findOrderDetailBySale(int SaleId);
    List<OrderDetail> findAllOrderDetail();
}
