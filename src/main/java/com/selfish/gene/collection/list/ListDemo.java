package com.selfish.gene.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> skills = new ArrayList<>();
        skills.add(new String("Java"));
        skills.add(new String("Maven"));
        skills.add(new String("Git"));
        System.out.println(skills);
        // 指定位置插入之后，其他元素依次移动
        skills.add(1, new String("Bazel"));
        System.out.println(skills);
        // 注意这里添加是一个新的字符串对象，但依然可以找到，因为List的indexOf方法通过equals()方法比较返回true即可
        System.out.println(skills.indexOf(new String("Bazel")));
        // set方法指定替换为元素及其位置，返回被替换元素
        String set = skills.set(2, "Bazel");
        System.out.println(set);
        System.out.println(skills);
        // 包含begin，不包含end
        System.out.println(skills.subList(0,2));

        // 程序调删除A对象时，会调用A对象的equals()方法与集合中的元素比较，因为重写equals()之后始终返回true，所以每回都会删除一个元素且总是一个元素
        List<String> frameworks = new ArrayList<>();
        frameworks.add("spring");
        frameworks.add("mybatis");
        frameworks.add("guava");
        frameworks.add("commons");
        System.out.println(frameworks);
        frameworks.remove(new A());
        System.out.println(frameworks);
        frameworks.remove(new A());
        System.out.println(frameworks);
        frameworks.remove(new A());
        System.out.println(frameworks);

        List<String> tools = new ArrayList<>();
        tools.add("idea");
        tools.add("git");
        tools.add("everything");
        tools.add("ditto");
        tools.add("typora");
        // 使用目标类型为Comparator的Lambda表达式对List集合排序
        tools.sort((s1, s2) -> (s1.length() - s2.length()));
        System.out.println(tools);
        // 使用目标类型为UnaryOperator的Lambda表达式来替换集合中所有元素
        // 该Lambda表达式控制使用每个字符串的长度作为新的集合元素
        tools.replaceAll(ele -> String.valueOf(ele.length()));
        System.out.println(tools);
    }
}
