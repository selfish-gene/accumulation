package com.selfish.gene.generic;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Canvas {
    public void drawAll(List<? extends Shape> shapes){
        for (Shape shape : shapes){
            Shape s = shape;
            s.draw(this);
        }
    }
}
