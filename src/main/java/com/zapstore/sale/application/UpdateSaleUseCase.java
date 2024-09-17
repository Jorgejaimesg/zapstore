package com.zapstore.sale.application;

import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class UpdateSaleUseCase {
    private final SaleService saleService;

    public UpdateSaleUseCase(SaleService saleService){
        this.saleService = saleService;
    }

    public void execute(Sale Sale){
        saleService.updateSale(Sale);
    }
}
