package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/11.
 */
public class A1 extends Apple<String> {

    @Override
    public String getInfo() {
        return "sub_class" + super.getInfo();
    }

    @Override
    public void setInfo(String info) {
        super.setInfo(info);
    }
}
