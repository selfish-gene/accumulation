package com.selfish.gene.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17.
 */
public class GenericDemo {
    private Map<String, Integer> score;

    public static void main(String[] args) throws Exception {
        Class<GenericDemo> clazz = GenericDemo.class;
        Field score = clazz.getDeclaredField("score");
        // 直接使用getType()取出的类型只对普通类型的成员变量有效,下面将看到仅输出java.util.Map
        Class<?> type = score.getType();
        System.out.println(type);
        // 获得成员变量f的泛型类型
        Type genericType = score.getGenericType();
        // 如果gType类型是ParameterizedType对象
        if (genericType instanceof ParameterizedType){
            // 强制类型转换
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type rawType = parameterizedType.getRawType();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Arrays.stream(actualTypeArguments).forEach(type1 -> System.out.println(type1));
        } else {
            System.out.println("获取泛型类型出错！");
        }
    }
}
