package com.selfish.jene.io.serializable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class Person implements java.io.Serializable {
    //Serialization can be independent of the object from the program
    private String name;
    private int age;

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
