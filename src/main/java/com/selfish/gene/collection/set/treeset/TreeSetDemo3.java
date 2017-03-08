package com.selfish.gene.collection.set.treeset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TreeSetDemo3 {
    /**
    * Get the log object
    */
     private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        TreeSet<R> ts = new TreeSet();
        ts.add(new R(5));
        ts.add(new R(-3));
        ts.add(new R(9));
        ts.add(new R(-2));
        ts.add(new R(-7));
        // 输出元素，有序
        System.out.println(ts);
        R first = ts.first();
        first.count = 20;
        // 对最后一个元素赋值，使其与第三个元素的count相等
        R last = ts.last();
        last.count = -2;
        // 输出元素，无序，且有重复元素
        System.out.println(ts);
        // 删除实例变量被修改的元素，删除失败
        LOGGER.info("begin delete modified elements");
        // TODO　与书上的不一致，没有被修改的实例删除成功，修改过的删除失败
        System.out.println(ts.remove(new R(-2)));
        System.out.println(ts);
        LOGGER.info("begin delete not modified elements");
        System.out.println(ts.remove(new R(5)));
        System.out.println(ts);
        LOGGER.info("begin delete modified elements again");
        // 删除成功，但没有排序
        System.out.println(ts.remove(new R(-2)));
        System.out.println(ts);
    }
}
