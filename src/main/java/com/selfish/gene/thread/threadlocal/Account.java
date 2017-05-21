package com.selfish.gene.thread.threadlocal;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Account {
    //定义一个ThreadLocal类型的变量，该变最将是一个线程局部变量，每个线程都会保留该变量的一个副本
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public Account(String name) {
        this.threadLocal.set(name);
        //下面代码用于访问当前线程的name副本的值
        System.out.println("---" + this.threadLocal.get());
    }

    public String getName() {
        return threadLocal.get();
    }

    public void setName(String name) {
        this.threadLocal.set(name);
    }
}
