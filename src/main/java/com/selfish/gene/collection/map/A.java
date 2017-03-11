package com.selfish.gene.collection.map;

/**
 * Created by Administrator on 2017/3/11.
 */
public class A {
    int count;

    public A(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        // 根据count的值来判断两个对象是否相等
        if (obj != null && obj.getClass() == A.class){
            A a = (A)obj;
            return this.count == a.count;
        }
        return false;
    }

    // 根据count来计算hashCode值
    @Override
    public int hashCode() {
        return this.count;
    }
}
