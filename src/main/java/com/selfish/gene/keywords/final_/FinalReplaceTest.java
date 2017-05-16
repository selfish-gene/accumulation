package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/15.
 */
public class FinalReplaceTest {
    public static void main(String[] args) throws Exception {
        final int a = 4;
        final double d = 1.2/3;
        final String str = "love" + "Java";
        final String book = "Java" + 50;
        // 无法在编译时确定book2的值，book2不会被当成宏变量
        final String book2 = "Java" + String.valueOf(50);
        System.out.println(book == "Java50");
        System.out.println(book2 == "Java50");

        String s1 = "loveJava";
        String s2 = "love" + "Java";
        System.out.println(s1 == s2);

        // str1与str2只是2个普通变量，编译器不会执行宏替换
        String str1 = "love";
        String str2 = "Java";
        String s3 = str1 + str2;
        System.out.println(s1 == s3);
    }
}
