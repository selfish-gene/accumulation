package com.selfish.gene.innerclass.static_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class StaticInnerClassDemo {
    private int prop1 = 5;
    private static int prop2 = 9;
    static class StaticInnerClass{
        private static int in1 = 5;
        private int in2 = 5;
        //静态内部类里可以包含静态成员
        private static int age;
        public void accessOuterProp(){
            // 静态内部类无法访问外部类的实例变量
//            System.out.println(prop1);
            System.out.println(prop2);
        }
    }

    public void accessInnerProp(){
        System.out.println(StaticInnerClass.in1);
        System.out.println(new StaticInnerClass().in2);
    }
}
