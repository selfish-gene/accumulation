package com.selfish.gene.classloader;

/**
 * Created by Administrator on 2017/1/12.
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        cl.loadClass("com.selfish.jene.classloader.MyTest");

        System.out.println("The system loads the MyTest class");
        Class.forName("com.selfish.jene.classloader.MyTest");
    }
}
