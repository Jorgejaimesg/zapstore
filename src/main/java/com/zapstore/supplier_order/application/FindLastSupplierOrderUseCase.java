package com.zapstore.supplier_order.application;

import java.util.Optional;
import com.zapstore.supplier_order.domain.entity.SupplierOrder;
import com.zapstore.supplier_order.domain.service.SupplierOrderService;

public class FindLastSupplierOrderUseCase {
    private final SupplierOrderService supplier_orderService;

    public FindLastSupplierOrderUseCase(SupplierOrderService supplier_orderService){
        this.supplier_orderService = supplier_orderService;
    }

    public Optional<SupplierOrder> findLastSupplierOrder(){
        return supplier_orderService.findLastSupplierOrder();
    }
}
