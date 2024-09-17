package com.zapstore.status_sale.application;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class DeleteStatusSaleUseCase {
    private final StatusSaleService status_saleService;

    public DeleteStatusSaleUseCase (StatusSaleService status_saleService) {
        this.status_saleService = status_saleService;
    }

    public StatusSale execute(String Name) {
        return status_saleService.deleteStatusSale(Name);
    }
}
