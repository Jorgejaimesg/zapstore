package com.zapstore.supplier_order.domain.entity;

public class SupplierOrder {
    private int id;
    private float total_price;
    private String date;
    private int payment_method_id, status_id;

    
    public SupplierOrder(int id, float total_price, String date, int payment_method_id, int status_id) {
        this.id = id;
        this.total_price = total_price;
        this.date = date;
        this.payment_method_id = payment_method_id;
        this.status_id = status_id;
    }
    public SupplierOrder(int id, float total_price, int payment_method_id, int status_id) {
        this.id = id;
        this.total_price = total_price;
        this.payment_method_id = payment_method_id;
        this.status_id = status_id;
    }
    public SupplierOrder() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getTotal_price() {
        return total_price;
    }
    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getPayment_method_id() {
        return payment_method_id;
    }
    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }
    public int getStatus_id() {
        return status_id;
    }
    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
 
}