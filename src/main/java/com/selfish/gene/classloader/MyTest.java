package com.selfish.gene.classloader;

/**
 * Created by Administrator on 2017/1/12.
 */
public class MyTest {
    static {
        System.out.println("The static block is being initialized ...");
    }
    static final String compileConstant = "selfish gene";

    public static void main(String[] args) throws Exception {
        System.out.println(MyTest.compileConstant);
    }
}
