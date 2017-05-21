package com.selfish.gene.thread.thread.group;

/**
 * Created by Administrator on 2017/5/20.
 */
public class ThreadGroupDemo {
    public static void main(String[] args) throws Exception {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组名：" + threadGroup);
        System.out.println("主线程是够后台线程组：" + threadGroup.isDaemon());
        new MyThread("主线程组的线程").start();

        ThreadGroup newGroup = new ThreadGroup("newGroup");
        newGroup.setDaemon(true);
        new MyThread(newGroup,"newGroup的线程_1").start();
        new MyThread(newGroup,"newGroup的线程_2").start();
    }
}
