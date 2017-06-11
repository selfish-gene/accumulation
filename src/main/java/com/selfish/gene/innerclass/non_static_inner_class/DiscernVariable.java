package com.selfish.gene.innerclass.non_static_inner_class;

/**
 * Created by Administrator on 2017/5/26.
 */
public class DiscernVariable {

    private String prop = "Var of Outer";
    private class Inner{
        private String prop = "Var of Inner";
        public void info(){
            String prop = "Var of Local";
            //通过OuterClass.this.propName的形式访问外部类的实例变量
            System.out.println(DiscernVariable.this.prop);
            //通过this.propName的形式访问非静态内部类的实例变量
            System.out.println(this.prop);
            System.out.println(prop);
        }
    }

    public void test(){
        Inner inner = new Inner();
        inner.info();
    }

    public static void main(String[] args) throws Exception {
        new DiscernVariable().test();
    }
}
