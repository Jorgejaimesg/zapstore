package com.zapstore.country.domain.entity;

public class Country {
    private String codeCountry;
    private String name;
    public Country() {
    }
    public Country(String codeCountry, String name) {
        this.codeCountry = codeCountry;
        this.name = name;
    }
    public String getCodeCountry() {
        return codeCountry;
    }
    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
