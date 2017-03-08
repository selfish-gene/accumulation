package com.selfish.gene.collection.set.linkedhashset;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class LinkedHashSetDemo {
    public static void main(String[] args) {
        // LinkedHashSet维护元素的插入顺序，性能略低于HashSet,但在迭代访问时会有较好的性能
        LinkedHashSet skills = new LinkedHashSet();
        skills.add("java");
        skills.add("bazel");
        skills.add("maven");
        skills.add("angularJS");
        System.out.println(skills);

        HashSet skillset = new HashSet();
        skillset.add("java");
        skillset.add("bazel");
        skillset.add("maven");
        skillset.add("angularJS");
        System.out.println(skillset);
    }
}
