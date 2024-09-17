package com.zapstore.customer_phone.domain.entity;

public class CustomerPhone {
    private int id;
    private String customer_id;
    private long phone_number;
    public CustomerPhone() {
    }
    public CustomerPhone(int id, String customer_id, long phone_number) {
        this.id = id;
        this.customer_id = customer_id;
        this.phone_number = phone_number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    public long getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    
}
