package com.selfish.gene.collection.map;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/11.
 */
public class MapDemo {
    public static void main(String[] args) {
        // key不能重复，value可以重复
        Map<String, Object> map = new HashMap<>();
        map.put("Java", 1);
        map.put("Maven", 2);
        map.put("Bazel", 2);
        map.put("Linux", 3);
        // 如果key重复，新的值会覆盖旧的值，pu方法返回旧的值
        assertThat(map.put("Linux", 2), is(3));
        assertTrue(map.containsKey("Java"));
        assertTrue(map.containsValue(2));

        // 尝试替换key为"sell"的value，由于原Map中没有对应的key，因此对Map没有改变，不会添加新的key-value对
        map.replace("shell", 4);
        System.out.println(map);
        // 使用原value与参数计算出来的结果覆盖原有的value，linux的value增大了10
        map.merge("linux", 10, (oldVal, param) -> (Integer)oldVal + (Integer)param);
        System.out.println(map);
        // 当key为"AngularJs"对应的value为null（或不存在时），使用计算的结果作为新value
        map.computeIfAbsent("AngularJs", key -> key.length());
        System.out.println(map);
        // 当key为"Java"对应的value存在时，使用计算的结果作为新value
        map.computeIfPresent("Bazel", (key, value) -> (Integer)value * (Integer)value);
        System.out.println(map);
    }
}
