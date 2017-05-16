package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/15.
 */
public class FinalVariableTest {

    final int a = 6;

    final String str;
    final int c;
    final static double d;

    final  int age;

    {
//        System.out.println(age);
        str = "final";
//        a = 8;
        age = 10;
    }

    static
    {
        d = 6.2;
    }

    public FinalVariableTest() {
//        str = "java";
        c = 5;
    }

    public static void main(String[] args) throws Exception {
        FinalVariableTest ft = new FinalVariableTest();
        System.out.println(ft.a);
        System.out.println(ft.c);
        System.out.println(ft.d);
    }
}
