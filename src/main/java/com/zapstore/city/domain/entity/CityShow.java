package com.zapstore.city.domain.entity;

public class CityShow {
    private String codecity;
    private String namecity;
    private String namereg;
    private String nameCountry;
    public CityShow() {
    }
    public CityShow(String codecity, String namecity, String namereg, String nameCountry) {
        this.codecity = codecity;
        this.namecity = namecity;
        this.namereg = namereg;
        this.nameCountry = nameCountry;
    }
    public String getCodecity() {
        return codecity;
    }
    public void setCodecity(String codecity) {
        this.codecity = codecity;
    }
    public String getNamecity() {
        return namecity;
    }
    public void setNamecity(String namecity) {
        this.namecity = namecity;
    }
    public String getNamereg() {
        return namereg;
    }
    public void setNamereg(String namereg) {
        this.namereg = namereg;
    }
    public String getNameCountry() {
        return nameCountry;
    }
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
