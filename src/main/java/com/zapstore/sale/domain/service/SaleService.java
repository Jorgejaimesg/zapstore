package com.zapstore.sale.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.sale.domain.entity.Sale;

public interface SaleService {
    void createSale(Sale sale);
    void updateSale(Sale sale);
    void deleteSale(int saleId);
    Optional<Sale> findSaleById(int saleId);
    List<Sale> findAllSale();
    Optional<Sale> findLastSale();
}
