package com.zapstore.sale_detail.application;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class UpdateSaleDetailUseCase {
    private final SaleDetailService sale_detailService;

    public UpdateSaleDetailUseCase(SaleDetailService sale_detailService){
        this.sale_detailService = sale_detailService;
    }

    public void execute(SaleDetail SaleDetail){
        sale_detailService.updateSaleDetail(SaleDetail);
    }
}
