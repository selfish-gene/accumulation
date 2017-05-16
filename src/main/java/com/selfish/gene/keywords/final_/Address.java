package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/15.
 */
public class Address {
   private final String detail;
   private final String postCode;

    public Address() {
        this.detail = "";
        this.postCode = "";
    }

    public Address(String detail, String postCode) {
        this.detail = detail;
        this.postCode = postCode;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getPostCode() {
        return this.postCode;
    }

    @Override
    public int hashCode() {
        return detail.hashCode() + postCode.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        //当detail和postCode相等时，可认为两个Address对象相等
        if(obj != null && obj.getClass() == Address.class){
            Address ad = (Address) obj;
            if(this.getDetail().equals(ad.getDetail()) && this.getPostCode().equals(ad.getPostCode())){
                return true;
            }
        }
        return false;
    }
}
