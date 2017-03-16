package com.selfish.gene.reflect.DynaProxy;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Demo {
    public static void main(String[] args) throws Exception{
        MyInvocationHandler handler = new MyInvocationHandler();
        Dog dog = (Dog) handler.getProxyInstance(new GunGog());
        dog.info();
        dog.run();
    }
}
