package com.selfish.gene.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ErrorAndRightDemo {
    static <T> void error(Collection<T> from, Collection<T> to){
        for (T t : from){
            to.add(t);
        }
    }

    static <T> void right(Collection<? extends T> from, Collection<T> to){
        for (T t : from){
            to.add(t);
        }
    }

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
//        error(stringList, objectList);
        right(stringList, objectList);
    }
}
