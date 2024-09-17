package com.zapstore.supplier_phone.domain.entity;

public class SupplierPhone {
    private int id;
    private String supplier_id;
    private long phone_number;
    public SupplierPhone() {
    }
    public SupplierPhone(int id, String supplier_id, long phone_number) {
        this.id = id;
        this.supplier_id = supplier_id;
        this.phone_number = phone_number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSupplier_id() {
        return supplier_id;
    }
    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }
    public long getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    
}
