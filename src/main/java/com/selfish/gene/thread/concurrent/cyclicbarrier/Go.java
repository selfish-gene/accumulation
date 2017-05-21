package com.selfish.gene.thread.concurrent.cyclicbarrier;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Go implements Runnable {
    @Override
    public void run() {
        System.out.println("人来齐了，Ready to go!!!");
    }
}
