package com.selfish.gene.reflect;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Person {

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
