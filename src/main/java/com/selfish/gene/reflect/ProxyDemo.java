package com.selfish.gene.reflect;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ProxyDemo {
    public static void main(String[] args) {
        People p = (People) Proxy.newProxyInstance(People.class.getClassLoader(),new Class[]{People.class}, new MyInvocationHandler());
        p.walk();
        p.sayHello("selfish_gene");
    }
}
