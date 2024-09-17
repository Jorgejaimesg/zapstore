package com.zapstore.sale_detail.application;

import java.util.Optional;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class FindSaleDetailByIdUseCase {
    private final SaleDetailService sale_detailService;

    public FindSaleDetailByIdUseCase(SaleDetailService sale_detailService){
        this.sale_detailService = sale_detailService;
    }

    public Optional<SaleDetail> findSaleDetailById(int id){
        return sale_detailService.findSaleDetailById(id);
    }
}
