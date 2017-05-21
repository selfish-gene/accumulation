package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class SecondThread implements Runnable{

    private int i;

    @Override
    public void run() {
        for(; i < 50; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                SecondThread st = new SecondThread();
                new Thread(st, "new_thread_1").start();
                new Thread(st, "new_thread_2").start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
