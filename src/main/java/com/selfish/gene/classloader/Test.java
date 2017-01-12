package com.selfish.gene.classloader;

/**
 * Created by Administrator on 2017/1/12.
 */
public class Test {
    static {
        b = 6;
    }
    static int a = 5;
    static int b = 9;
    static int c;

    public static void main(String[] args) throws Exception {
        System.out.println(Test.b);
    }
}
