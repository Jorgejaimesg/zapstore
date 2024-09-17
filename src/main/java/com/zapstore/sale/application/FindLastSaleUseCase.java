package com.zapstore.sale.application;

import java.util.Optional;
import com.zapstore.sale.domain.entity.Sale;
import com.zapstore.sale.domain.service.SaleService;

public class FindLastSaleUseCase {
    private final SaleService saleService;

    public FindLastSaleUseCase(SaleService saleService){
        this.saleService = saleService;
    }

    public Optional<Sale> findLastSale(){
        return saleService.findLastSale();
    }
}
