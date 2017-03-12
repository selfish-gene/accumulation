package com.selfish.gene.collection.collections;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by Administrator on 2017/3/11.
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(10);
        nums.add(-3);
        nums.add(5);
        nums.add(-9);
        nums.add(5);
        System.out.println(nums);
        Collections.sort(nums);
        System.out.println(nums);
        Collections.reverse(nums);
        System.out.println(nums);
        Collections.shuffle(nums);
        System.out.println(nums);
        assertThat(Collections.max(nums), is(10));
        assertThat(Collections.min(nums), is(-9));
        assertTrue(Collections.replaceAll(nums, 5, 10));
        assertThat(Collections.frequency(nums, 10), is(3));
        // 自然排序从小到大，只有排序后的集合才能用二分法查找
        Collections.sort(nums);
        System.out.println(nums);
        assertThat(Collections.binarySearch(nums, 10), is(4));
        Collections.reverse(nums);
        System.out.println(nums);
        assertThat(Collections.binarySearch(nums, 10), is(2));

        // 创建线程安全的集合
        List list = Collections.synchronizedList(new ArrayList<>());
        Map map = Collections.synchronizedMap(new HashMap<>());
        Set set = Collections.synchronizedSet(new HashSet<>());

        // 创建不可修改的集合
        List unmodifiableList = Collections.emptyList();
        Set unmodifiableSet = Collections.singleton("Set");
        Map commonMap = new HashMap();
        commonMap.put("Java", 1);
        commonMap.put("Maven", 2);
        Map unmodifiableMap = Collections.unmodifiableMap(commonMap);
        // 下面任意一行代码都将引发UnsupportedOperationException异常
        try {
            unmodifiableList.add("Linux");
            unmodifiableMap.put("Linux", 1);
            unmodifiableSet.add("Linux");
        } catch (UnsupportedOperationException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
            assert true;
        }

    }
}
