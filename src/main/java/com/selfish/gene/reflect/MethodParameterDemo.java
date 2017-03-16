package com.selfish.gene.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
public class MethodParameterDemo {
    public static void main(String[] args) throws Exception {
        Class<Demo> clazz = Demo.class;
        Method replace = clazz.getMethod("replace", String.class, List.class);

        System.out.println(replace.getParameterCount());
        Parameter[] replaceParameters = replace.getParameters();
        Arrays.stream(replaceParameters).forEach(parameter -> {
            //需要指出的是，使用javac命令编译Java源文件时，默认生成的class文件并不包含方法的形参名信息，因此调用isNamePresent()方法将会返回false，
            if (parameter.isNamePresent()) {
                System.out.println("参数名：" + parameter.getName());
                System.out.println("形参类型：" + parameter.getType());
                System.out.println("泛型类型：" + parameter.getParameterizedType());
            }
        });
    }
}
