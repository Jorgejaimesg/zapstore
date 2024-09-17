package com.zapstore.product.domain.entity;

public class Product {
    private int id, category_id, stock, stock_min, brand_id;
    private Float sale_price;
    private String reference, name;

    
    public Product(int id, int category_id, String reference,int stock, int stock_min, String name, int brand_id, Float sale_price
            ) {
        this.id = id;
        this.category_id = category_id;
        this.reference = reference;
        this.stock = stock;
        this.stock_min = stock_min;
        this.name = name;
        this.brand_id = brand_id;
        this.sale_price = sale_price;
    }
    public Product() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCategory_id() {
        return category_id;
    }
    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getStock_min() {
        return stock_min;
    }
    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }
    public int getBrand_id() {
        return brand_id;
    }
    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }
    public Float getSale_price() {
        return sale_price;
    }
    public void setSale_price(Float sale_price) {
        this.sale_price = sale_price;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
