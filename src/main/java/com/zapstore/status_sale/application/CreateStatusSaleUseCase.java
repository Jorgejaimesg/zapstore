package com.zapstore.status_sale.application;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class CreateStatusSaleUseCase {
    private final StatusSaleService status_saleService;

    public CreateStatusSaleUseCase(StatusSaleService status_saleService){
        this.status_saleService = status_saleService;
    }

    public void execute(StatusSale StatusSale){
        status_saleService.createStatusSale(StatusSale);
    }
}
