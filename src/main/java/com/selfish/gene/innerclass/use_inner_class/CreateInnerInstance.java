package com.selfish.gene.innerclass.use_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class CreateInnerInstance {

    public static void main(String[] args) throws Exception {
        //由于非静态内部类的对象必须寄生在外部类的对象里，因此创建非静态内部类对象之前，必须先创建其外部类对象
        Out.In in = new Out().new In("Just for test");
    }
}

class Out {
    class In{
        public In(String msg){
            System.out.println(msg);
        }
    }
}
