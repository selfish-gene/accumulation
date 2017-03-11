package com.selfish.gene.collection.map;

import java.util.TreeMap;

/**
 * Created by Administrator on 2017/3/11.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap tm = new TreeMap();
        tm.put(new R(1), "Java");
        tm.put(new R(-7), "Bazel");
        tm.put(new R(9), "Linux");
        System.out.println(tm);
        System.out.println(tm.firstEntry());
        System.out.println(tm.lastKey());
        System.out.println(tm.higherKey(new R(-1)));
        System.out.println(tm.lowerKey(new R(-1)));
        System.out.println(tm.lowerEntry(new R(-1)));
        System.out.println(tm.subMap(new R(-1), new R(3)));
    }
}
