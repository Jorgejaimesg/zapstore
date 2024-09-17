package com.zapstore.supplier_order.application;

import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class DeleteSupplierOrderUseCase {
    private final SupplierOrderService supplier_orderService;

    public DeleteSupplierOrderUseCase(SupplierOrderService supplier_orderService){
        this.supplier_orderService = supplier_orderService;
    }

    public void execute(int SupplierOrderId){
        supplier_orderService.deleteSupplierOrder(SupplierOrderId);
    }
}
