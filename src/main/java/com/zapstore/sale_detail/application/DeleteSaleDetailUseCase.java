package com.zapstore.sale_detail.application;

import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class DeleteSaleDetailUseCase {
    private final SaleDetailService sale_detailService;

    public DeleteSaleDetailUseCase(SaleDetailService sale_detailService){
        this.sale_detailService = sale_detailService;
    }

    public void execute(int SaleDetailId){
        sale_detailService.deleteSaleDetail(SaleDetailId);
    }
}
