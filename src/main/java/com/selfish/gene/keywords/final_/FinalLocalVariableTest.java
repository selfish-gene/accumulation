package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/15.
 */
public class FinalLocalVariableTest {
    public void test(final int a){
//        a = 5;
        System.out.println(a);
    }

    public static void main(String[] args) throws Exception {
        final String str = "Hello";
//        str = "java";
        FinalLocalVariableTest fv = new FinalLocalVariableTest();
        fv.test(5);

        final double d;
        d = 5.6;
//        d= 7.8;
        System.out.println(d);
    }
}
