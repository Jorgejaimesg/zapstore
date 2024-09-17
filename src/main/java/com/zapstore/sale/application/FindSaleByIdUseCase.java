package com.zapstore.sale.application;

import java.util.Optional;

import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class FindSaleByIdUseCase {
    private final SaleService saleService;

    public FindSaleByIdUseCase(SaleService saleService){
        this.saleService = saleService;
    }

    public Optional<Sale> findSaleById(int id){
        return saleService.findSaleById(id);
    }
}
