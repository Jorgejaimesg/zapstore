package com.zapstore.supplier_order.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.supplier_order.domain.entity.SupplierOrder;

public interface SupplierOrderService {
    void createSupplierOrder(SupplierOrder supplier_order);
    void updateSupplierOrder(SupplierOrder supplier_order);
    void deleteSupplierOrder(int supplier_orderId);
    Optional<SupplierOrder> findSupplierOrderById(int supplier_orderId);
    List<SupplierOrder> findAllSupplierOrder();
    Optional<SupplierOrder> findLastSupplierOrder();
}
