package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/12.
 */
public class GenericConstructor {
    public static void main(String[] args) {
        new Foo("Java");
        new Foo(102);

        // 显式指定泛型构造器中的T参数为String，
        // 传给Foo构造器的实参也是String对象，完全正确。
        new <String> Foo("Git");
        // 显式指定泛型构造器中的T参数为String，
        // 但传给Foo构造器的实参是Double对象，下面代码出错
//        new <String> Foo(12.3);

    }

    static class Foo{
        public <T> Foo(T t){
            System.out.println(t);
        }
    }
}
