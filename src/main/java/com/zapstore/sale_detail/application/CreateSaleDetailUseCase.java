package com.zapstore.sale_detail.application;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class CreateSaleDetailUseCase {
    private final SaleDetailService sale_detailService;

    public CreateSaleDetailUseCase(SaleDetailService sale_detailService){
        this.sale_detailService = sale_detailService;
    }

    public void execute(SaleDetail SaleDetail){
        sale_detailService.createSaleDetail(SaleDetail);
    }
}
