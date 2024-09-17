package com.zapstore.region.domain.entity;

public class Region {
    private String codereg;
    private String namereg;
    private String codeCountry;
    public Region() {
    }
    public Region(String codereg, String namereg, String codeCountry) {
        this.codereg = codereg;
        this.namereg = namereg;
        this.codeCountry = codeCountry;
    }
    public String getCodereg() {
        return codereg;
    }
    public void setCodereg(String codereg) {
        this.codereg = codereg;
    }
    public String getNamereg() {
        return namereg;
    }
    public void setNamereg(String namereg) {
        this.namereg = namereg;
    }
    public String getCodeCountry() {
        return codeCountry;
    }
    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }
}
