package com.hkq.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Zz {
    private BigDecimal id;

    private String zbrand;

    private String zorigin;

    private String zprice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date zdateinproduced;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date zsalesdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date zexpirationdate;

    private BigDecimal flavorid;

    private Flavorid flavor;

    List<Img> imgList = new ArrayList<>();

    public void setImgList(List<Img> imgList) {
        this.imgList = imgList;
    }

    public List<Img> getImgList() {

        return imgList;
    }

    public void setFlavor(Flavorid flavor) {
        this.flavor = flavor;
    }

    public Flavorid getFlavor() {

        return flavor;
    }

    public Zz() {
        super();
    }

    public Zz(BigDecimal id, String zbrand, String zorigin, String zprice, Date zdateinproduced, Date zsalesdate, Date zexpirationdate, BigDecimal flavorid, Flavorid flavor) {
        this.id = id;
        this.zbrand = zbrand;
        this.zorigin = zorigin;
        this.zprice = zprice;
        this.zdateinproduced = zdateinproduced;
        this.zsalesdate = zsalesdate;
        this.zexpirationdate = zexpirationdate;
        this.flavorid = flavorid;
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "Zz{" +
                "id=" + id +
                ", zbrand='" + zbrand + '\'' +
                ", zorigin='" + zorigin + '\'' +
                ", zprice='" + zprice + '\'' +
                ", zdateinproduced=" + zdateinproduced +
                ", zsalesdate=" + zsalesdate +
                ", zexpirationdate=" + zexpirationdate +
                ", flavorid=" + flavorid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zz zz = (Zz) o;

        if (!id.equals(zz.id)) return false;
        if (!zbrand.equals(zz.zbrand)) return false;
        if (!zorigin.equals(zz.zorigin)) return false;
        if (!zprice.equals(zz.zprice)) return false;
        if (!zdateinproduced.equals(zz.zdateinproduced)) return false;
        if (!zsalesdate.equals(zz.zsalesdate)) return false;
        if (!zexpirationdate.equals(zz.zexpirationdate)) return false;
        return flavorid.equals(zz.flavorid);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + zbrand.hashCode();
        result = 31 * result + zorigin.hashCode();
        result = 31 * result + zprice.hashCode();
        result = 31 * result + zdateinproduced.hashCode();
        result = 31 * result + zsalesdate.hashCode();
        result = 31 * result + zexpirationdate.hashCode();
        result = 31 * result + flavorid.hashCode();
        return result;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {

        return id;
    }

    public String getZbrand() {
        return zbrand;
    }

    public void setZbrand(String zbrand) {
        this.zbrand = zbrand == null ? null : zbrand.trim();
    }

    public String getZorigin() {
        return zorigin;
    }

    public void setZorigin(String zorigin) {
        this.zorigin = zorigin == null ? null : zorigin.trim();
    }

    public String getZprice() {
        return zprice;
    }

    public void setZprice(String zprice) {
        this.zprice = zprice == null ? null : zprice.trim();
    }

    public Date getZdateinproduced() {
        return zdateinproduced;
    }

    public void setZdateinproduced(Date zdateinproduced) {
        this.zdateinproduced = zdateinproduced;
    }

    public Date getZsalesdate() {
        return zsalesdate;
    }

    public void setZsalesdate(Date zsalesdate) {
        this.zsalesdate = zsalesdate;
    }

    public Date getZexpirationdate() {
        return zexpirationdate;
    }

    public void setZexpirationdate(Date zexpirationdate) {
        this.zexpirationdate = zexpirationdate;
    }

    public void setFlavorid(BigDecimal flavorid) {
        this.flavorid = flavorid;
    }

    public BigDecimal getFlavorid() {

        return flavorid;
    }
}