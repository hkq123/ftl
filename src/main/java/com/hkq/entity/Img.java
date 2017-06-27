package com.hkq.entity;

import java.math.BigDecimal;

public class Img {
    private BigDecimal iid;

    private String iname;

    private String iuuidname;

    private BigDecimal id;

    public Img() {
        super();
    }

    public Img(BigDecimal iid, String iname, String iuuidname, BigDecimal id) {
        this.iid = iid;
        this.iname = iname;
        this.iuuidname = iuuidname;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Img{" +
                "iid=" + iid +
                ", iname='" + iname + '\'' +
                ", iuuidname='" + iuuidname + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Img img = (Img) o;

        if (!iid.equals(img.iid)) return false;
        if (!iname.equals(img.iname)) return false;
        if (!iuuidname.equals(img.iuuidname)) return false;
        return id.equals(img.id);
    }

    @Override
    public int hashCode() {
        int result = iid.hashCode();
        result = 31 * result + iname.hashCode();
        result = 31 * result + iuuidname.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    public void setIid(BigDecimal iid) {
        this.iid = iid;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIid() {
        return iid;
    }

    public BigDecimal getId() {
        return id;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname == null ? null : iname.trim();
    }

    public String getIuuidname() {
        return iuuidname;
    }

    public void setIuuidname(String iuuidname) {
        this.iuuidname = iuuidname == null ? null : iuuidname.trim();
    }


}