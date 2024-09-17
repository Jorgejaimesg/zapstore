package com.zapstore.sale_detail.application;

import java.util.List;

import com.zapstore.sale_detail.domain.entity.SaleDetail;
import com.zapstore.sale_detail.domain.service.SaleDetailService;

public class FindSaleDetailBySale {
    private final SaleDetailService sale_detailService;

    public FindSaleDetailBySale(SaleDetailService sale_detailService) {
        this.sale_detailService = sale_detailService;
    }

    public List<SaleDetail> findSaleDetailBySale(int SaleId) {
        return sale_detailService.findSaleDetailBySale(SaleId);
    }
}
