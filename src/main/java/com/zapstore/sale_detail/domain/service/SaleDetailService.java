package com.zapstore.sale_detail.domain.service;

import java.util.List;
import java.util.Optional;

import com.zapstore.sale_detail.domain.entity.SaleDetail;

public interface SaleDetailService {
    void createSaleDetail(SaleDetail sale_detail);
    void updateSaleDetail(SaleDetail sale_detail);
    void deleteSaleDetail(int sale_detailId);
    Optional<SaleDetail> findSaleDetailById(int sale_detailId);
    List<SaleDetail> findSaleDetailBySale(int SaleId);
    List<SaleDetail> findAllSaleDetail();
}
