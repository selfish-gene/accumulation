package com.selfish.gene.collection.map;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/11.
 */
public class HashMapErrorDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(new A(23457), "Java");
        map.put(new A(44212), "Bazel");

        Iterator iterator = map.keySet().iterator();
        // 取出Map中第一个key，并修改它的count值
        A first = (A) iterator.next();
        first.count = 44212;
        // 此时两个key相等
        System.out.println(map);
        // 只能删除没有被修改过的key所对应的key-value对
        map.remove(new A(44212));
        System.out.println(map);
        // 无法获取剩下的value，下面两行代码都将输出null。尽量不要使用可变对象最为key，如果使用则不要在程序中作出修改key的行为
        assertNull(map.get(new A(44212)));
        assertNull(map.get(new A(23457)));

    }
}
