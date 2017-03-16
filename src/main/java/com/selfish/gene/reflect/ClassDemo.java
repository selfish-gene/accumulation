package com.selfish.gene.reflect;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/1/14.
 */
@SuppressWarnings(value = "unchecked")
@Deprecated
@Anno
@Anno
public class ClassDemo {
    // 为该类定义一个私有的构造器
    private ClassDemo()
    {
    }
    // 定义一个有参数的构造器
    public ClassDemo(String name)
    {
        System.out.println("执行有参数的构造器");
    }
    // 定义一个无参数的info方法
    public void info()
    {
        System.out.println("执行无参数的info方法");
    }
    // 定义一个有参数的info方法
    public void info(String str)
    {
        System.out.println("执行有参数的info方法"
                + "，其str参数值：" + str);
    }
    // 定义一个测试用的内部类
    class Inner
    {
    }

    public static void main(String[] args) throws Exception{
        // 下面代码可以获取ClassTest对应的Class
        Class<ClassDemo> clazz = ClassDemo.class;
        // 获取该Class对象所对应类的全部构造器
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(constructor ->  System.out.println(constructor));
        // 获取该Class对象所对应类的全部public构造器
        Constructor[] publicConstructors = clazz.getConstructors();
        Arrays.stream(publicConstructors).forEach(constructor ->  System.out.println(constructor));
        // 获取该Class对象所对应类的全部public方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> System.out.println(method));
        // 获取该Class对象所对应类的指定方法
        Method info = clazz.getMethod("info", String.class);
        System.out.println(info);
        // 获取该Class对象所对应类的上的全部注解
        Annotation[] annotations = clazz.getAnnotations();
        Arrays.stream(annotations).forEach(annotation -> System.out.println(annotation));
        // 获取该Class对象所对应类的全部内部类
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        Arrays.stream(declaredClasses).forEach(classInner -> System.out.println(classInner));

        System.out.println("ClassTest的包为：" + clazz.getPackage());
        System.out.println("ClassTest的父类为：" + clazz.getSuperclass());

    }
}
