package com.selfish.gene.collection.map;

import java.util.HashMap;
import java.util.Hashtable;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/11.
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(new A(23457), "Java");
        map.put(new A(44212), "Bazel");
        B b1 = new B();
        map.put(new A(1232), b1);
        System.out.println(map);
        B b2 = new B();
        assertTrue(map.containsValue(b2));
        // 不重写equals方法，调用Object的equals比较的是内存地址，则会返回false
        assertTrue(b1.equals(b2));
        // TODO 这里equals返回的是true，但是HashMap的containsValue返回却是false，为什么？
        System.out.println(map.containsValue("b"));
        assertTrue(b1.equals("b"));
        // 只要两个A对象的count相等，它们通过equals比较返回true，且hashCode相等
        // HashMap即认为它们是相同的key，所以下面输出true。
        assertTrue(map.containsKey(new A(44212)));
        // 但其实这两个对象key不同
        assertFalse(new A(44212) == new A(44212));

        // 下面语句可以删除最后一个key-value对
        map.remove(new A(1232));
        System.out.println(map);

        Hashtable ht = new Hashtable();
        ht.put(new A(60000) , "Java");
        ht.put(new A(87563) , "Bazel");
        ht.put(new A(1232) , new B());
        System.out.println(ht);
        // 只要两个对象通过equals比较返回true，
        // Hashtable就认为它们是相等的value。
        // 由于Hashtable中有一个B对象，
        // 它与任何对象通过equals比较都相等，所以下面输出true。
        // TODO 而这里equals返回的是true，但是Hashtable的containsValue返回的也是true，为什么？
        assertTrue(new B().equals("测试字符串"));
        assertTrue(ht.containsValue("测试字符串"));
        // 只要两个A对象的count相等，它们通过equals比较返回true，且hashCode相等
        // Hashtable即认为它们是相同的key，所以下面输出true。
        assertTrue(ht.containsKey(new A(87563)));
        // 下面语句可以删除最后一个key-value对
        ht.remove(new A(1232));
        System.out.println(ht);
    }
}
