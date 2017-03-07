package com.selfish.gene.collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/3/7.
 */
public class IteratorDemo {

    /**
    * Get the log object
    */
     private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        Collection<String> skills = new HashSet<>();
        skills.add("java");
        skills.add("bazel");
        skills.add("maven");
        skills.add("git");
        skills.add("nope");

        Iterator<String> iterator = skills.iterator();
        iterator.forEachRemaining(obj -> System.out.println("迭代集合元素：" + obj));
        while (iterator.hasNext()){
            String skill = iterator.next();
            System.out.println(skill);
            if (skill.equals("nope")){
                // 从集合中删除上一次next()方法返回的元素
                iterator.remove();
//                使用Iterator迭代过程中，不可修改集合元素,下面代码引发异常 java.util.ConcurrentModificationException
//                skills.remove(skill);
            }
            // 对skill变量赋值，不会改变集合元素本身
            skill = "test";
        }
        System.out.println(skills);

    }

}
