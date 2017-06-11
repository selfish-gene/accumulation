package com.selfish.gene.innerclass.local_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class LocalInnerClass {
    public static void main(String[] args) throws Exception {
        class InnerBase {
            int a;
        }
        class InnerSub extends InnerBase {
            int b;
        }
        InnerSub sub = new InnerSub();
        sub.a = 5;
        sub.b = 8;
        System.out.println();
    }
}
