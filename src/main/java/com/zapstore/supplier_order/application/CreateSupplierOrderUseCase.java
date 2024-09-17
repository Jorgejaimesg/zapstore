package com.zapstore.supplier_order.application;

import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class CreateSupplierOrderUseCase {
    private final SupplierOrderService supplier_orderService;

    public CreateSupplierOrderUseCase(SupplierOrderService supplier_orderService){
        this.supplier_orderService = supplier_orderService;
    }

    public void execute(SupplierOrder SupplierOrder){
        supplier_orderService.createSupplierOrder(SupplierOrder);
    }
}
