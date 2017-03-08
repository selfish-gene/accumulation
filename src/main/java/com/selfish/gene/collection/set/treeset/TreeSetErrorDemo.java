package com.selfish.gene.collection.set.treeset;

import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TreeSetErrorDemo {
    public static void main(String[] args) {
        // TreeSet只能默认添加实现Comparable接口的类，如BigDecimal,String,Date
        TreeSet ts_1 = new TreeSet();
//        ts_1.add(new Error());
//        ts_1.add(new Error());

        // TreeSet中应该添加同一个类的对象，因为它会调用该对象的compareTo方法去和其它元素比较
        TreeSet ts_2 = new TreeSet();
        ts_2.add(new String("Just for test"));
        ts_2.add(new Date());
    }
}
