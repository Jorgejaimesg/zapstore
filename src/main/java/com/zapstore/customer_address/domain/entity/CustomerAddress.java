package com.zapstore.customer_address.domain.entity;

public class CustomerAddress {
    private int id;
    private int zipCode;
    private String Details;
    private String city_id;
    private String customer_id;
    public CustomerAddress() {
    }
    public CustomerAddress(int id, int zipCode, String details, String city_id, String customer_id) {
        this.id = id;
        this.zipCode = zipCode;
        Details = details;
        this.city_id = city_id;
        this.customer_id = customer_id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public String getDetails() {
        return Details;
    }
    public void setDetails(String details) {
        Details = details;
    }
    public String getCity_id() {
        return city_id;
    }
    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
