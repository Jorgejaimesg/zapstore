package com.zapstore.payment_method.domain.entity;

public class PaymentMethod {
    private int id;
    private String name;
    public PaymentMethod() {
    }
    public PaymentMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
