package com.selfish.gene.innerclass.anonymous_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class ADemo {
    public static void main(String[] args) throws Exception {
        int age = 5;
        A a = new A() {
            @Override
            public void test() {
                // 被匿名内部类或局部内部类访问的局部变量默认使用final修饰，因此该变量初始化之后不能被重新赋值
                System.out.println(age);
            }
        };
    }
}

interface A{
    void test();
}
