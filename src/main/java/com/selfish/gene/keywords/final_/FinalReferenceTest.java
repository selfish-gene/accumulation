package com.selfish.gene.keywords.final_;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/5/15.
 */
public class FinalReferenceTest {

    public static void main(String[] args) throws Exception {
        final int[] arr = new int[]{5,7,9};
        System.out.println(Arrays.toString(arr));
        arr[2] = -10;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //重新赋值，非法
//        arr = null;

        final Person p = new Person();
        p.setAge(10);
        System.out.println(p.getAge());
        p.setAge(20);
        System.out.println(p.getAge());

        //重新赋值，非法
//        p = new Person();
    }
}
