package com.selfish.gene.collection.set.enumSet;

import java.time.Month;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/9.
 */
public class EnumSetDemo {
    public static void main(String[] args) {
        // 创建一个EnumSet集合，集合元素就是Month枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Month.class);
        System.out.println(es1);
        // 创建一个空集合，集合元素就是Month枚举类的全部枚举值
        EnumSet es2 = EnumSet.noneOf(Month.class);
        System.out.println(es2);
        es2.add(Month.MARCH);
        es2.add(Month.APRIL);
        System.out.println(es2);
        // 以制定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Month.APRIL, Month.DECEMBER);
        System.out.println(es3);
        EnumSet es4 = EnumSet.range(Month.APRIL, Month.OCTOBER);
        System.out.println(es4);
        // 新创建的EnumSet集合和es4集合有相同的类型，es5 + es4 = Month枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        System.out.println(es5);

        Collection c = new HashSet<>();
        c.add(Month.FEBRUARY);
        c.add(Month.MAY);
        // 复制Collection集合中的所有元素来创建EnumSet集合
        EnumSet es = EnumSet.copyOf(c);
        System.out.println(es);
        // 如果Collection中不全是Month枚举类型，则会报错。
        //c.add("This say error");
        es = EnumSet.copyOf(c);
    }

}
