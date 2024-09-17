package com.zapstore.status_sale.domain.entity;

public class StatusSale {
    private int id;
    private String name;
    public StatusSale() {
    }
    public StatusSale(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescriptionmode() {
        return name;
    }
    public void setDescriptionmode(String name) {
        this.name = name;
    }
    
}
