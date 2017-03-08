package com.selfish.gene.collection.set.treeset;

import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TreeSetDemo2 {
    public static void main(String[] args) {
        // TreeSet判断元素相等的唯一标准是：两个对象通过compareTo(Object obj)方法比较是否返回0，0相等，否则不相等
        TreeSet set = new TreeSet();
        Z z = new Z(5);
        set.add(z);
        // 第二次添加同一个对象，返回true，表明添加成功
        System.out.println(set.add(z));
        System.out.println(set);
        // 修改set集合第一个元素的age变量
        ((Z)set.first()).age = 9;
        // 输出set集合最后一个元素的age变量，将看到也变成9
        System.out.println(((Z)set.last()).age);
    }
}
