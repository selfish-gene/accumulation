package com.selfish.gene.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ListIteratorDemo {
    public static void main(String[] args) {
        List<String> tools = new ArrayList<>();
        tools.add("idea");
        tools.add("git");
        tools.add("everything");
        tools.add("ditto");
        tools.add("typora");
       ListIterator<String> listIterator = tools.listIterator();
       while (listIterator.hasNext()){
           System.out.println(listIterator.next());
           listIterator.add("---------------");
       }
       System.out.println("=======下面开始反向迭代=======");
       while (listIterator.hasPrevious()){
           System.out.println(listIterator.previous());
       }
    }
}
