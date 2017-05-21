package com.selfish.gene.thread.communication.problem_case.plan3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Product {

    private BlockingQueue<Integer> bq;
    private int n;

    public Product(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    /**
     * for consumer
     * @return
     */
    public int get(){
        try {
            n = bq.take();
            System.out.println("消费：" + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * for producer
     * @param n
     */
    public void put(int n){
        try {
            bq.put(n);
            System.out.println("生产：" + n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
