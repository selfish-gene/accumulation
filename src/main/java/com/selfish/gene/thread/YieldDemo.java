package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class YieldDemo extends Thread{

    public YieldDemo(String name) {
        super(name);
    }

    public void run(){
        for (int i = 0; i < 10; i++){
            System.out.println(getName() + "" + i);
            if(i == 5){
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        YieldDemo y1 = new YieldDemo("high");
        y1.setPriority(Thread.MAX_PRIORITY);
        y1.start();
        y1.join();

        YieldDemo y2 = new YieldDemo("low");
        y2.setPriority(Thread.MIN_PRIORITY);
        y2.start();
        y2.join();
        System.out.println(Thread.currentThread().getPriority());
    }
}
