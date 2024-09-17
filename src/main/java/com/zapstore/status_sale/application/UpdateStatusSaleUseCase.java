package com.zapstore.status_sale.application;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class UpdateStatusSaleUseCase {
    private final StatusSaleService status_saleService;

    public UpdateStatusSaleUseCase(StatusSaleService status_saleService){
        this.status_saleService = status_saleService;
    }

    public void execute(StatusSale status_sale){
        status_saleService.updateStatusSale(status_sale);
    }
}
