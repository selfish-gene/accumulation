package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/11.
 */
public class A2 extends Apple {
    @Override
    public String getInfo() {
        // super.getInfo()方法返回值是Object类型，
        // 所以加toString()才返回String类型
        return super.getInfo().toString();
    }
}
