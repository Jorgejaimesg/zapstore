package com.zapstore.status_sale.application;

import java.util.List;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class FindAllStatusSaleUseCase {
    private final StatusSaleService status_saleService;

    public FindAllStatusSaleUseCase(StatusSaleService status_saleService) {
        this.status_saleService = status_saleService;
    }

    public List<StatusSale> findAllStatusSale() {
        return status_saleService.findAllStatusSale();
    }
}
