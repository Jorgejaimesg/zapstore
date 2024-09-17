package com.zapstore.sale_detail.application;

import java.util.List;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class FindAllSaleDetailUseCase {
    private final SaleDetailService sale_detailService;

    public FindAllSaleDetailUseCase(SaleDetailService sale_detailService) {
        this.sale_detailService = sale_detailService;
    }

    public List<SaleDetail> findAllSaleDetail() {
        return sale_detailService.findAllSaleDetail();
    }
}
