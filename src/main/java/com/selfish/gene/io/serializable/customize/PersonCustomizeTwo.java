package com.selfish.gene.io.serializable.customize;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/5.
 */
public class PersonCustomizeTwo implements Serializable{

    private String name;
    private int age;

    public PersonCustomizeTwo(String name, int age) {
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

    private Object writeReplace() throws ObjectStreamException{
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(age);
        return list;
    }
}
