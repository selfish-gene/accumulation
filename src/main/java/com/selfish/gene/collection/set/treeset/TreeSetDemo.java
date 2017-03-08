package com.selfish.gene.collection.set;

import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TreeSetDemo {

    public static void main(String[] args) {
        TreeSet nums = new TreeSet();
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);
        nums.add(-3);
        // 输出集合元素，已经处于排序状态
        System.out.println(nums);
        System.out.println(nums.first());
        System.out.println(nums.last());
        // 返回小于4的子集
        System.out.println(nums.headSet(4));
        // 返回大于5的子集，包含5
        System.out.println(nums.tailSet(5));
        // 返回大于等于-3小于4的子集，包含5
        System.out.println(nums.subSet(-3,4));
    }
}
