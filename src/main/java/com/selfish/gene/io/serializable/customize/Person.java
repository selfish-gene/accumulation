package com.selfish.gene.io.serializable.customize;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/5.
 */
public class Person implements Serializable{

    private String name;
    private transient int age;

    public Person(String name, int age){
        System.out.println("Constructor with arguments");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
