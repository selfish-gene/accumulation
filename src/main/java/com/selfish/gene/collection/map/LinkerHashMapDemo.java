package com.selfish.gene.collection.map;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/3/11.
 */
public class LinkerHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap scores = new LinkedHashMap();
        scores.put("语文", 78);
        scores.put("英语", 89);
        scores.put("数学", 98);
        scores.forEach((key, value) -> System.out.println(key + " --> " + value));
    }
}
