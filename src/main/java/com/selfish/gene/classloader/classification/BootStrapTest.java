package com.selfish.gene.classloader.classification;

import java.net.URL;

/**
 * Created by Administrator on 2017/1/12.
 */
public class BootStrapTest {
    public static void main(String[] args) throws Exception {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++){
            System.out.println(urls[i].toExternalForm());
        }
    }
}
