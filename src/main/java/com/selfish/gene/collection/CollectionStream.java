package com.selfish.gene.collection;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/7.
 */
public class CollectionStream {
    public static void main(String[] args) {
        Collection<String> skills = new HashSet<>();
        skills.add("java");
        skills.add("bazel");
        skills.add("maven");
        skills.add("git");
        skills.add("nope");
        // 统计包含a的技能名称的数量
        System.out.println(skills.stream().filter(ele -> ele.contains("a")).count());
        // 统计技能名称长度大于4的数量
        System.out.println(skills.stream().filter(ele -> ele.length() > 4).count());

        // 先调用Collection对象的stream()方法转换为Stream
        // 在调用Stream的mapToInt()方法获取原有的Stream对应的IntStream
        System.out.println("****************************");
        skills.stream().mapToInt(ele -> ele.length())
                // 调用foreach()方法遍历IntStream中每个元素
        .forEach(System.out::println);
    }

}
