package com.zapstore.status_order.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.status_order.domain.entity.StatusOrder;

public interface StatusOrderService {
    void createStatusOrder(StatusOrder StatusOrder);
    void updateStatusOrder(StatusOrder StatusOrder);
    StatusOrder deleteStatusOrder(String name);
    Optional<StatusOrder> findStatusOrderByName (String name);
    Optional<StatusOrder> findStatusOrderById (int Id);
    List<StatusOrder> findAllStatusOrder();
}
