package com.selfish.gene.thread.thread.group;

/**
 * Created by Administrator on 2017/5/20.
 */
public class ExHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " 线程持出现异常：" + e);
    }

    public static void main(String[] args){
        Thread.currentThread().setUncaughtExceptionHandler(new ExHandler());
        int a = 5/0;
        System.out.println("the end");
    }
}
