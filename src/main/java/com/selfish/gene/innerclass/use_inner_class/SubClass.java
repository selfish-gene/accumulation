package com.selfish.gene.innerclass.use_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class SubClass extends Out.In {
    /**
     * 当创建一个子类时，子类构造器总会调用父类的构造器，因此在创建非静态内部类的子类时，必须
     * 保证让子类构造器可以调用非静态内部类的构造器，调用非静态内部类的构造器时，必须存在一个外部
     * 类对象。
     * @param out 外部类对象
     */
    public SubClass(Out out) {
        out.super("hello");
    }
}
