package com.selfish.gene.collection.map;

import java.time.Month;
import java.util.EnumMap;

/**
 * Created by Administrator on 2017/3/11.
 */
public class EnumMapDemo {
    public static void main(String[] args) {
        // EnumMap创建时必须指定一个枚举类
        EnumMap em = new EnumMap(Month.class);
        em.put(Month.APRIL, 4);
        em.forEach((key, value) -> System.out.println(key + " --> " + value));
    }
}
