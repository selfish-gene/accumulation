package com.selfish.gene.classes.commons;

/**
 * Created by Administrator on 2017/3/12.
 */
public class CloneDemo {

    static class Address{
        String detail;

        public Address(String detail) {
            this.detail = detail;
        }
    }
    static class User implements Cloneable{
        int age;
        Address address;

        public User(int age) {
            this.age = age;
            address = new Address("sz_CN");
        }

        public User clone() throws CloneNotSupportedException{
            // 通过调用super.clone()来实现clone()方法
            return (User) super.clone();
        }
    }

    public static void main(String[] args) throws Exception{
        User u1 = new User(25);
        // clone得到u1对象的副本。
        User u2 = u1.clone();
        // 判断u1、u2是否相同
        System.out.println(u1 == u2);
        // 判断u1、u2的address是否相同
        System.out.println(u1.address == u2.address);
    }

}
