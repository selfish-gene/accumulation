package com.selfish.gene.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class FixedSizeList {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("books","skills","languages");
        // 获取fixedList的实现类，将输出Arrays$ArrayList
        System.out.println(items.getClass());
        items.stream().forEach(System.out::println);
        // 试图增加、删除元素都会引发UnsupportedOperationException异常
        try {
            items.add("money");
            items.remove("books");
        } catch (UnsupportedOperationException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
//            e.printStackTrace();
        }

    }
}
