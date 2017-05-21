package com.selfish.gene.thread.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/5/20.
 */
public class MainDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Go());
        //给定数量的Wait线程全部处于await()时，Go线程开始执行，然后Wait线程随机执行
        new Thread(new Wait(cyclicBarrier, "Allen"), "Allen").start();
        new Thread(new Wait(cyclicBarrier, "Edam")).start();
        new Thread(new Wait(cyclicBarrier, "A")).start();
    }
}
