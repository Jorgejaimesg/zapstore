package com.zapstore.customer.domain.entity;

public class Customer {
    private String id;
    private int customer_type;
    private int id_type;
    private String first_name;
    private String last_name;
    private String email;
    public Customer(String id, int customer_type, int id_type, String first_name, String last_name, String email) {
        this.id = id;
        this.customer_type = customer_type;
        this.id_type = id_type;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCustomer_type() {
        return customer_type;
    }
    public void setCustomer_type(int customer_type) {
        this.customer_type = customer_type;
    }
    public int getId_type() {
        return id_type;
    }
    public void setId_type(int id_type) {
        this.id_type = id_type;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Customer() {
    }


}
