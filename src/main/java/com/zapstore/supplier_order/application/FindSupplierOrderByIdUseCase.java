package com.zapstore.supplier_order.application;

import java.util.Optional;

import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class FindSupplierOrderByIdUseCase {
    private final SupplierOrderService supplier_orderService;

    public FindSupplierOrderByIdUseCase(SupplierOrderService supplier_orderService){
        this.supplier_orderService = supplier_orderService;
    }

    public Optional<SupplierOrder> findSupplierOrderById(int id){
        return supplier_orderService.findSupplierOrderById(id);
    }
}
