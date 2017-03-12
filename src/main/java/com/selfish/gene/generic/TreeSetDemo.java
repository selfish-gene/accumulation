package com.selfish.gene.generic;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/12.
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        // Comparator的实际类型是TreeSet的元素类型的父类，满足要求
        TreeSet<String> ts1 = new TreeSet<>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                return hashCode() > o2.hashCode() ? 1 : hashCode() < o2.hashCode() ? -1 : 0;
            }
        });
        ts1.add("Hello");
        ts1.add("Hi");
        ts1.add("Morning");
        System.out.println(ts1);
        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.length() > s2.length() ?- 1 : s1.length() < s2.length() ? 1 : 0;
            }
        });
        ts2.add("Hello");
        ts2.add("Hi");
        ts2.add("Morning");
        System.out.println(ts2);
    }
}
