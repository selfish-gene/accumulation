package com.selfish.gene.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public class ErasureDemo {

    static class Apple<T extends Number>{
        T size;

        public Apple() {
        }

        public Apple(T size) {
            this.size = size;
        }

        public T getSize() {
            return size;
        }

        public void setSize(T size) {
            this.size = size;
        }
    }

    public static void main(String[] args) {
        Apple<Integer> a = new Apple<>(6);
        // a的getSize方法返回Integer对象
        Integer ai = a.getSize();
        System.out.println(ai.getClass());
        // 把a对象赋给Apple变量，丢失尖括号里的类型信息
        Apple b = a;
        // b只知道size的类型是Number
        Number size = b.getSize();
        System.out.println(size.getClass());

        // 下面代码引起编译错误
//        Integer size2 = b.getSize();

        List<Integer> li = new ArrayList<>();
        li.add(6);
        li.add(9);
        // 丢失泛型信息
        List list = li;
        // 下面代码引起“未经检查的转换”的警告，编译、运行时完全正常
        List<String> ls = list;
        // 但只要访问ls里的元素，如下面代码将引起运行时异常
        System.out.println(ls.get(0));
    }
}
