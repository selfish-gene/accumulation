package com.selfish.gene.lambda;

import com.selfish.gene.lambda.function.Converter;
import com.selfish.gene.lambda.function.MyTest;
import com.selfish.gene.lambda.function.YourTest;

import javax.swing.*;

/**
 * Created by Administrator on 2017/3/12.
 */
public class LambdaDemo {
    public static void main(String[] args) {

        // 1.引用类方法
        Converter converter1 = from -> Integer.valueOf(from);
        System.out.println(converter1.convert("99"));

        // 函数式接口中被实现方法的全部参数传给该类方法作为参数，使用两个英文冒号
        Converter converter2 = Integer::valueOf;
        System.out.println(converter2.convert("32"));

        // 2.引用特定对象的实例方法
        Converter converter3 = from -> "Java".indexOf(from);
        System.out.println(converter3.convert("v"));
        // 函数式接口中被实现方法的全部参数传给该类方法作为参数
        Converter converter4 = "Java"::indexOf;
        System.out.println(converter4.convert("v"));

        // 3.引用某类对象的实例方法
        MyTest mt = (a, b, c) -> a.substring(b ,c);
        System.out.println(mt.test("I love Java", 2, 6));
        // 函数式接口中被实现方法的第一个参数作为调用者，后面的参数全部传给该方法作为参数
        MyTest mt2 = String::substring;
        System.out.println(mt2.test("I love Java", 2, 6));

        // 4.引用构造器
        YourTest yt = a -> new JFrame(a);
        System.out.println(yt.win("My title"));
        // 函数式接口中被实现方法的全部参数传给该类构造器作为参数
        YourTest yt2 = JFrame::new;
        System.out.println(yt2.win("My title"));


    }
}
