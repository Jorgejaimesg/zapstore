package com.zapstore.supplier_order.application;

import java.util.List;

import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class FindAllSupplierOrderUseCase {
    private final SupplierOrderService supplier_orderService;

    public FindAllSupplierOrderUseCase(SupplierOrderService supplier_orderService) {
        this.supplier_orderService = supplier_orderService;
    }

    public List<SupplierOrder> findAllSupplierOrder() {
        return supplier_orderService.findAllSupplierOrder();
    }
}
