package com.selfish.gene.classloader;

/**
 * Created by Administrator on 2017/1/12.
 */
public class ATest {
    public static void main(String[] args) throws Exception {
        //getA();
        getB();
    }

    public static void getA(){
        A a = new A();
        a.a ++;
        System.out.println(a.a);
    }

    public static void getB(){
        A b = new A();
        System.out.println(b.a);
    }
}
