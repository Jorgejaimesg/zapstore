package com.zapstore.status_sale.application;

import java.util.Optional;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class FindStatusSaleByIdUseCase {
    private final StatusSaleService status_saleService;
    public FindStatusSaleByIdUseCase(StatusSaleService status_saleService) {
        this.status_saleService = status_saleService;
    }

    public Optional<StatusSale> findStatusSaleById(int id) {
        return status_saleService.findStatusSaleById(id);
    }
}
