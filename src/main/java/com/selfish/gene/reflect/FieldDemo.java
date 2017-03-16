package com.selfish.gene.reflect;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/3/16.
 */
public class FieldDemo {
    public static void main(String[] args) throws Exception{
        Person p = new Person();
        Class<Person> personClass = Person.class;
        Field nameField = personClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p, "selfish_gene");
        Field ageField = personClass.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(p, 25);
        System.out.println(p);
    }
}
