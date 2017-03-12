package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/12.
 */
public class GenericDiamondDemo {
    public static void main(String[] args) {
        // MyClass类声明中的E形参是String类型。
        // 泛型构造器中声明的T形参是Integer类型
        MyClass<String> mc1 = new MyClass<>(1);
        // 显式指定泛型构造器中声明的T形参是Integer类型
        MyClass<String> mc2 = new <Integer> MyClass<String>(2);

        // 如果显式指定泛型构造器中声明的T形参是Integer类型
        // 此时就不能使用"菱形"语法，下面代码是错的。
//		MyClass<String> mc3 = new <Integer> MyClass<>(3);
        MyClass mc4 = new <Integer> MyClass(4);
    }

    static class MyClass<E> {
        public <T> MyClass(T t) {
            System.out.println("t is : " + t);
        }
    }
}
