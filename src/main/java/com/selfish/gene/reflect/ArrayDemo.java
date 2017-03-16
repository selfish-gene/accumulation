package com.selfish.gene.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ArrayDemo {
    public static void main(String[] args) {
        // 创建一个元素类型为String ，长度为10的数组
        Object arr = Array.newInstance(String.class, 10);
        Array.set(arr, 5, "Java");
        Array.set(arr, 6, "Maven");
        Object o5 = Array.get(arr, 5);
        Object o6 = Array.get(arr, 6);
        System.out.println(o5);
        System.out.println(o6);

        /*
		  创建一个三维数组。
		  根据前面介绍数组时讲的：三维数组也是一维数组，
		  是数组元素是二维数组的一维数组，
		  因此可以认为arr是长度为3的一维数组
		*/
        Object arr_3 = Array.newInstance(String.class, 3, 4, 10);
        // 获取arr_3数组中index为2的元素，该元素应该是二维数组
        Object arr_3_2  = Array.get(arr_3, 2);
        // 使用Array为二维数组的数组元素赋值。二维数组的数组元素是一维数组，
        // 所以传入Array的set()方法的第三个参数是一维数组。
        Array.set(arr_3_2, 2, new String[]{"Java", "Maven"});
        // 获取arr_3_2数组中index为3的元素，该元素应该是一维数组。
        Object arr_3_2_3 = Array.get(arr_3_2, 3);
        Array.set(arr_3_2_3, 8, "Bazel");
        String[][][] cast = (String[][][]) arr_3;
        // 遍历输出数组
        Arrays.stream(cast).forEach(twoArr -> Arrays.stream(twoArr).forEach(oneArr -> System.out.println(Arrays.toString(oneArr))));

    }
}
