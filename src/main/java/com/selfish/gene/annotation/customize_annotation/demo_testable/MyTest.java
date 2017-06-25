package com.selfish.gene.annotation.customize_annotation.demo_testable;

/**
 * Created by Administrator on 2017/6/25.
 */
public class MyTest {
    @Testable
    public static void m1(){}
    public static void m2(){}
    @Testable
    public static void m3(){
        throw new IllegalArgumentException("Wrong arguments!");
    }
    public static void m4(){}
    @Testable
    public static void m5(){
        System.out.println("Method5");
    }
    public static void m6(){}
    @Testable
    public static void m7(){
        throw new RuntimeException("Running error!");
    }
    public static void m8(){}
}
