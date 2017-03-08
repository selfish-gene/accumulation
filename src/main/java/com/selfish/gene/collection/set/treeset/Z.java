package com.selfish.gene.collection.set.treeset;

/**
 * Created by Administrator on 2017/3/8.
 */
public class Z implements Comparable {

    int age;

    public Z(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}
