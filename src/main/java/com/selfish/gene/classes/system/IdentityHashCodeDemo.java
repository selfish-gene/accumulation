package com.selfish.gene.classes.system;

/**
 * Created by Administrator on 2017/3/12.
 */
public class IdentityHashCodeDemo {
    public static void main(String[] args) {
        String s1 = new String("Java");
        String s2 = new String("Java");
        // String重写了hashCode()方法——改为根据字符序列计算hashCode值，
        // 因为s1和s2的字符序列相同，所以它们的hashCode方法返回值相同
        System.out.println(s1.hashCode() + "----" + s2.hashCode());
        // s1和s2是不同的字符串对象，所以它们的identityHashCode值不同
        System.out.println(System.identityHashCode(s1) + "----" + System.identityHashCode(s2));

        String s3 = "Java";
        String s4 = "Java";
        System.out.println(System.identityHashCode(s3) + "----" + System.identityHashCode(s4));
    }
}
