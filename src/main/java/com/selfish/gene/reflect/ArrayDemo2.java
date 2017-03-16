package com.selfish.gene.reflect;

import sun.swing.StringUIClientPropertyKey;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ArrayDemo2 {
    public static <T> T[] newInstance(Class<T> type, int length){
        return (T[]) Array.newInstance(type, length);
    }

    public static void main(String[] args) throws Exception{
        String[] strs = newInstance(String.class, 5);
        strs[2] = "Java";
        System.out.println(Arrays.toString(strs));
        int[][] ints = newInstance(int[].class, 5);
        ints[2] = new int[]{1,3,4};
        Arrays.stream(ints).forEach(ints1 -> System.out.println(Arrays.toString(ints1)));

    }
}
