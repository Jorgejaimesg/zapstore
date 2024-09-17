package com.zapstore.status_sale.application;

import java.util.Optional;

import com.zapstore.status_sale.domain.entity.StatusSale;
import com.zapstore.status_sale.domain.service.StatusSaleService;

public class FindStatusSaleByNameUseCase {
    private final StatusSaleService status_saleService;

    public FindStatusSaleByNameUseCase(StatusSaleService status_saleService) {
        this.status_saleService = status_saleService;
    }

    public Optional<StatusSale> findStatusSaleByName(String status_saleName) {
        return status_saleService.findStatusSaleByName(status_saleName);
    }
}
