package com.selfish.gene.collection.set.hashset;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/3/8.
 */
public class HashSetDemo2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new  R(-3));
        hs.add(new  R(9));
        hs.add(new  R(-2));
        System.out.println(hs);
        // 取出第一个元素
        Iterator iterator = hs.iterator();
        // 第一个元素为-2
        R first = (R) iterator.next();
        System.out.println(first);
        // 为第一个元素的count实例变量赋值，正好与已存在元素-3重复
        first.count = -3;
        System.out.println(hs);
        // 删除count为-3的R对象
        hs.remove(new R(-3));
        System.out.println("hs是否包含count为-3的R对象：" + hs.contains(new R(-3)));
        System.out.println("hs是否包含count为-2的R对象：" + hs.contains(new R(-2)));
    }
}
