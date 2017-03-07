package com.selfish.gene.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2017/3/7.
 */
public class PredicateDemo {

    public static void main(String[] args) {
        Collection<String> skills = new HashSet<>();
        skills.add("java");
        skills.add("bazel");
        skills.add("maven");
        skills.add("git");
        skills.add("nope");

        skills.removeIf(ele ->  ele.equals("nope"));
        System.out.println(skills);

        // 统计字母a出现的次数
        System.out.println(calAll(skills, ele->ele.contains("a")));

        // 统计字长度大于4的技能名称出现的次数
        System.out.println(calAll(skills, ele->ele.length()>4));
    }

    /**
     * 统计符合条件的数目
     * @param skills 目标集合
     * @param p 条件
     * @return 数目
     */
    public static int calAll(Collection<String> skills, Predicate<String> p){
        int total = 0;
        for (String s :skills){
            if (p.test(s)){
                total++;
            }
        }
        return total;
    }
}
