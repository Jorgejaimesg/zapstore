package com.zapstore.sale.application;

import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class CreateSaleUseCase {
    private final SaleService saleService;

    public CreateSaleUseCase(SaleService saleService){
        this.saleService = saleService;
    }

    public void execute(Sale Sale){
        saleService.createSale(Sale);
    }
}
