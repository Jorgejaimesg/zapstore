package com.zapstore.sale.application;

import com.zapstore.sale.domain.service.SaleService;

public class DeleteSaleUseCase {
    private final SaleService saleService;

    public DeleteSaleUseCase(SaleService saleService){
        this.saleService = saleService;
    }

    public void execute(int SaleId){
        saleService.deleteSale(SaleId);
    }
}
