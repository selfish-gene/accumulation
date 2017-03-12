package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Apple<T> {
    private T info;

    public Apple(T info) {
        this.info = info;
    }

    public Apple() {
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public static void main(String[] args) {
        Apple<String> stringApple = new Apple<>("apple");
        System.out.println(stringApple.getInfo());
        Apple<Double> doubleApple = new Apple<>(1.2);
        System.out.println(doubleApple.getInfo());

    }
}
