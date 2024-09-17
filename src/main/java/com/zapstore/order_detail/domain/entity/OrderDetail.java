package com.zapstore.order_detail.domain.entity;


public class OrderDetail {
    private int id;
    private int product_id, quantity, order_id;
    private Float purchase_price, sub_total;
    private String supplier_id;
    public OrderDetail() {
    }
    public OrderDetail(int id, int product_id, int quantity, int order_id, Float purchase_price, Float sub_total, String supplier_id) {
        this.id = id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.purchase_price = purchase_price;
        this.sub_total = sub_total;
        this.supplier_id = supplier_id;
    }
    public String getSupplier_id() {
        return supplier_id;
    }
    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
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
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public Float getPurchase_price() {
        return purchase_price;
    }
    public void setPurchase_price(Float purchase_price) {
        this.purchase_price = purchase_price;
    }
    public Float getSub_total() {
        return sub_total;
    }
    public void setSub_total(Float sub_total) {
        this.sub_total = sub_total;
    }

}
