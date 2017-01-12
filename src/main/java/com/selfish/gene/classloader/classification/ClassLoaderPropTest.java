package com.selfish.gene.classloader.classification;

import java.net.URL;
import java.util.Enumeration;

/**
 * Created by Administrator on 2017/1/12.
 */
public class ClassLoaderPropTest {
    public static void main(String[] args) throws Exception {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("System class loader:\t" + systemClassLoader);

        Enumeration<URL> em = systemClassLoader.getResources("");
        while(em.hasMoreElements()){
            System.out.println(em.nextElement());
        }

        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("Extension class loader:\t" + extensionLoader);
        System.out.println("The load path of Extension class loader:\t" + System.getProperty("java.ext.dirs"));
        //In fact, The parent of Extension ClassLoader is the BootStrap ClassLoader
        System.out.println("The parent of Extension class loader:\t" + extensionLoader.getParent());
    }
}
