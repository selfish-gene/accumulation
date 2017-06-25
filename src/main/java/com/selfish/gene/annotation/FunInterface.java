package com.selfish.gene.annotation;

/**
 * Created by Administrator on 2017/6/25.
 */
@FunctionalInterface
public interface FunInterface {
    void test();
    static void foo()
    {
        System.out.println("foo");
    }
    default void bar()
    {
        System.out.println("bar");
    }
}
