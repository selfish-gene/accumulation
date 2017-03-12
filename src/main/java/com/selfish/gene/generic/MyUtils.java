package com.selfish.gene.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class MyUtils {

    // 下面dest集合元素类型必须与src集合元素类型相同，或是其父类
    static <T> T copy(Collection<? super T> dest, Collection<T> src){
        T last = null;
        for (T ele : src){
            last = ele;
            dest.add(ele);
        }
        return last;
    }

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(5);
        // 此处可准确的知道最后一个被复制的元素是Integer类型
        // 与src集合元素的类型相同
        Integer last = copy(numberList, integerList);
        System.out.println(last);
    }
}
