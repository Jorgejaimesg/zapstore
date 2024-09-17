package com.zapstore.sale_detail.domain.entity;

public class SaleDetail {
    private int id;
    private int product_id, quantity, sale_id;
    private Float sale_price, sub_total;
    public SaleDetail() {
    }
    public SaleDetail(int id, int product_id, int quantity, int sale_id, Float sale_price, Float sub_total) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.sale_id = sale_id;
        this.sale_price = sale_price;
        this.sub_total = sub_total;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getSale_id() {
        return sale_id;
    }
    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }
    public Float getSale_price() {
        return sale_price;
    }
    public void setSale_price(Float sale_price) {
        this.sale_price = sale_price;
    }
    public Float getSub_total() {
        return sub_total;
    }
    public void setSub_total(Float sub_total) {
        this.sub_total = sub_total;
    }

}
