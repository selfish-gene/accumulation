package com.selfish.gene.generic;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Circle extends Shape{
    @Override
    public void draw(Canvas canvas) {
        System.out.println("draw a circle on " + canvas );
    }
}
