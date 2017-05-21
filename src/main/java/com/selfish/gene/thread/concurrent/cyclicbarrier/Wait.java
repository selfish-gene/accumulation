package com.selfish.gene.thread.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Wait implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private String name;

    public Wait(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is waiting");
            cyclicBarrier.await();
            if(Thread.currentThread().getName().equals("Allen")){
                System.out.println("我等的时间最长，终于可以走了！");
            } else{
                System.out.println("哈哈，一起走吧!!!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
