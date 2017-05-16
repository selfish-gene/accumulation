package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class IntegerCacheTest {
    public static void main(String[] args) throws Exception {
        Integer in1 = new Integer(10);
        Integer in2 = Integer.valueOf(10);
        Integer in3 = Integer.valueOf(10);
        System.out.println(in1 == in2); // false
        System.out.println(in2 == in3); // true

        // 由于Integer只缓存-128~127之间的值，因此200对应的Integer对象没有被缓存。
        Integer in4 = Integer.valueOf(200);
        Integer in5 = Integer.valueOf(200);
        System.out.println(in4 == in5); // false
    }
}
