package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class DaemonThread extends Thread{

    public void run(){
        for (int i = 0; i < 1000; i++){
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws Exception {
        DaemonThread dt = new DaemonThread();
        dt.setDaemon(true);
        dt.start();
        for (int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        //程序到此结束，前台线程（main）结束，后台线程也随之结束。
    }
}
