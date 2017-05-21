package com.selfish.gene.keywords.this_;

/**
 * Created by Administrator on 2017/5/21.
 */
public class ReturnThis {

    public int age;
    public ReturnThis add(){
        age++;
        return this;
    }

    public static void main(String[] args) throws Exception {
        ReturnThis rt = new ReturnThis();
        rt.add().add().add();
        System.out.println(rt.age); //输出3，调用三次add方法，age自增三次
    }
}
