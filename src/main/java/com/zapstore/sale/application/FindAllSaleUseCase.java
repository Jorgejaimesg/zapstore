package com.zapstore.sale.application;

import java.util.List;

import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class FindAllSaleUseCase {
    private final SaleService saleService;

    public FindAllSaleUseCase(SaleService saleService) {
        this.saleService = saleService;
    }

    public List<Sale> findAllSale() {
        return saleService.findAllSale();
    }
}
