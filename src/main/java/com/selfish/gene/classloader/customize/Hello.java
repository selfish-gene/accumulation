package com.selfish.gene.classloader.customize;

/**
 * Created by Administrator on 2017/1/13.
 */
public class Hello {
    public static void main(String[] args) throws Exception {
        for (String arg : args){
            System.out.println("parameter to run the Hello is" + arg);
        }
    }
}
