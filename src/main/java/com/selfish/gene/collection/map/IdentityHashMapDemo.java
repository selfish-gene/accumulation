package com.selfish.gene.collection.map;

import java.util.IdentityHashMap;

/**
 * Created by Administrator on 2017/3/11.
 */
public class IdentityHashMapDemo {
    public static void main(String[] args) {
        // IdentityHashMap要求两个key严格相等时(==)才认为key相等
        IdentityHashMap ihm = new IdentityHashMap();
        // 下面两行代码将会向IdentityHashMap对象中添加两个key-value对
        ihm.put(new String("语文"), 78);
        ihm.put(new String("数学"), 89);
        // 下面两行代码只会向IdentityHashMap对象中添加一个key-value对
        ihm.put("java", 68);
        ihm.put("java", 98);
        System.out.println(ihm);
    }
}
