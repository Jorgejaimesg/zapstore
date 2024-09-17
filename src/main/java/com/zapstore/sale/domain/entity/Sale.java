package com.zapstore.sale.domain.entity;

public class Sale {
    private int id;
    private float total_price, discount_amount, discount_percent;
    private String customer_id, date;
    private int payment_method_id, status_id;
    public Sale(int id, float total_price, float discount_amount, float discount_percent, String customer_id,
            int payment_method_id, int status_id) {
        this.id = id;
        this.total_price = total_price;
        this.discount_amount = discount_amount;
        this.discount_percent = discount_percent;
        this.customer_id = customer_id;
        this.payment_method_id = payment_method_id;
        this.status_id = status_id;
    }

    public Sale(int id, float total_price, float discount_amount, float discount_percent, String customer_id, int payment_method_id, int status_id, String date) {
        this.id = id;
        this.total_price = total_price;
        this.discount_amount = discount_amount;
        this.discount_percent = discount_percent;
        this.customer_id = customer_id;
        this.payment_method_id = payment_method_id;
        this.status_id = status_id;
        this.date = date;
    }
    public Sale() {
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
    public float getDiscount_amount() {
        return discount_amount;
    }
    public void setDiscount_amount(float discount_amount) {
        this.discount_amount = discount_amount;
    }
    public float getDiscount_percent() {
        return discount_percent;
    }
    public void setDiscount_percent(float discount_percent) {
        this.discount_percent = discount_percent;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
