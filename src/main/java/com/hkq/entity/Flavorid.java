package com.hkq.entity;

import java.math.BigDecimal;

public class Flavorid {
    private BigDecimal flavorid;

    private String fname;

    @Override
    public String toString() {
        return "Flavorid{" +
                "flavorid=" + flavorid +
                ", fname='" + fname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flavorid flavorid1 = (Flavorid) o;

        if (flavorid != null ? !flavorid.equals(flavorid1.flavorid) : flavorid1.flavorid != null) return false;
        return fname != null ? fname.equals(flavorid1.fname) : flavorid1.fname == null;
    }

    @Override
    public int hashCode() {
        int result = flavorid != null ? flavorid.hashCode() : 0;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        return result;
    }

    public Flavorid() {
        super();
    }

    public Flavorid(BigDecimal flavorid, String fname) {
        this.flavorid = flavorid;
        this.fname = fname;
    }

    public void setFlavorid(BigDecimal flavorid) {
        this.flavorid = flavorid;
    }

    public BigDecimal getFlavorid() {
        return flavorid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }
}