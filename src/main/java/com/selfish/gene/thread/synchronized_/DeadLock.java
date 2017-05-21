package com.selfish.gene.thread.synchronized_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    public void init(){
        Thread.currentThread().setName("primary");
        a.userA(b);
        System.out.println("进入primary线程");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("vice");
        b.userB(a);
        System.out.println("进入vice线程");
    }

    public static void main(String[] args) throws Exception {
        DeadLock deadLock = new DeadLock();
        Thread thread = new Thread(deadLock);
        thread.start();
        //在此处通过join控制，必须等thread线程执行完毕，main线程才能继续往下执行，则不会出现死锁。
//        thread.join();

        // 调用init方法
        deadLock.init();
        /*死锁发生的原因：
            1.两个线程执行需要一定的时间（在各自的同步方法里有sleep了200毫秒）
            2.两个线程开始执行的顺序没有进行控制
            3.两个线程互相持有对方的锁（同步监视器）
         */
    }
}
