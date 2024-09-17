package com.zapstore.supplier.domain.entity;

public class Supplier {
    private String nit;
    private int supplier_type;
    private String name;
    private String email;
    private String city_id;
    public Supplier() {
    }
    
    public Supplier(String nit, int supplier_type, String name, String email, String city_id) {
        this.nit = nit;
        this.supplier_type = supplier_type;
        this.name = name;
        this.email = email;
        this.city_id = city_id;
    }

    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    public int getSupplier_type() {
        return supplier_type;
    }
    public void setSupplier_type(int supplier_type) {
        this.supplier_type = supplier_type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCity_id() {
        return city_id;
    }
    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
