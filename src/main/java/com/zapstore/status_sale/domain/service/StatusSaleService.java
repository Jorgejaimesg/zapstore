package com.zapstore.status_sale.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.status_sale.domain.entity.StatusSale;

public interface StatusSaleService {
    void createStatusSale(StatusSale StatusSale);
    void updateStatusSale(StatusSale StatusSale);
    StatusSale deleteStatusSale(String name);
    Optional<StatusSale> findStatusSaleByName (String name);
    Optional<StatusSale> findStatusSaleById (int Id);
    List<StatusSale> findAllStatusSale();
}
