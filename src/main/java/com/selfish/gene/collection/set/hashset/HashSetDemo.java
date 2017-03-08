package com.selfish.gene.collection.set.hashset;

import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class HashSetDemo {
    public static void main(String[] args) {
        // HashSet判断两个对象是否相等：equals返回true，hashCode相等
        HashSet set = new HashSet();
        // A由于hashCode不同，被判断为两个对象,添加成功
        set.add(new A());
        set.add(new A());
        // B由于equals返回false，被判断为两个对象,添加成功
        set.add(new B());
        set.add(new B());
        // C由于判断为重复元素添加失败
        set.add(new C());
        set.add(new C());
        System.out.println(set);
    }
}
