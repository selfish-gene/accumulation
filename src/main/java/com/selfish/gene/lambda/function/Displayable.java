package com.selfish.gene.lambda.function;

/**
 * Created by Administrator on 2017/3/12.
 */
@FunctionalInterface
public interface Displayable {
    void display();
    default int add(int a, int b){
        return a + b;
    }
}
