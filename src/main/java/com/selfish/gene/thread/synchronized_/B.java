package com.selfish.gene.thread.synchronized_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class B {
    public synchronized void userB(A a){
        System.out.println(Thread.currentThread().getName() + " userB方法");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 企图调用A实例的last方法");
        a.last();
    }
    public synchronized void last(){
        System.out.println("调用B的last方法");
    }
}
