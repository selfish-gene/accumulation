package com.selfish.gene.thread.synchronized_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class A {
    public synchronized void userA(B b){
        System.out.println(Thread.currentThread().getName() + " userA方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 企图调用B实例的last方法");
        b.last();
    }
    public synchronized void last(){
        System.out.println("调用A的last方法");
    }
}
