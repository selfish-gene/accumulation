package com.selfish.gene.innerclass.non_static_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Outer {
    private int outProp = 10;

    class Inner {
        private int inProp = 5;
        public void accessOuterProp(){
            System.out.println("直接访问外部类的属性，outProp = " + outProp);
        }
    }

    public void accessInnerProp(){
        // 外部类不能直接访问非静态内部类的实例变量，如需访问，必须显示创建内部类对象
        System.out.println("内部类的属性，inProp = " + new Inner().inProp);
        new Inner().accessOuterProp();
    }

    public static void main(String[] args) throws Exception {
        Outer outer = new Outer();
        outer.accessInnerProp();
    }
}
