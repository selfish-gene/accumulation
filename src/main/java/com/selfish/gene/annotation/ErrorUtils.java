package com.selfish.gene.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/25.
 */
public class ErrorUtils
{

    public static void main(String[] args) {
        List<String>[] strings = new List[5];
        faultyMethod(Arrays.asList("Hello"), Arrays.asList("World"));
    }

    public static void faultyMethod(List<String>...listStrArray)
    {
        // TODO 堆污染，暂时不太理解
        List[] listArray = listStrArray;
        List<Integer> myList = new ArrayList<>();
        myList.add(new Random().nextInt(100));
        listArray[0] = myList;
        String s = listStrArray[0].get(0);
        System.out.println(s);
    }
}
