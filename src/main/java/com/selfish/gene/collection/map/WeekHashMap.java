package com.selfish.gene.collection.map;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2017/3/11.
 */
public class WeekHashMap {
    public static void main(String[] args) {
        WeakHashMap whm = new WeakHashMap();
        whm.put(new String("Java"), new String("Good"));
        whm.put(new String("Bazel"), new String("Medium"));
        whm.put(new String("Linux"), new String("Medium"));
        //将 WeakHashMap中添加一个key-value对，
        // 该key是一个系统缓存的字符串对象。
        whm.put("java", new String("Medium"));
        System.out.println(whm);
        // 通知系统立即进行垃圾回收
        System.gc();
        System.runFinalization();
        // 通常情况下，将只看到一个key-value对。
        System.out.println(whm);
    }
}
