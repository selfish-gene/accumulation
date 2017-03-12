package com.selfish.gene.classes.commons;

import java.util.Objects;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ObjectsDemo {

    static ObjectsDemo objectsDemo;

    public static void main(String[] args) {
        System.out.println(Objects.hashCode(objectsDemo));
        System.out.println(Objects.toString(objectsDemo));
        try {
            System.out.println(Objects.requireNonNull(objectsDemo));
        } catch (NullPointerException e) {
            System.out.println(e.getClass());
        }
    }
}
