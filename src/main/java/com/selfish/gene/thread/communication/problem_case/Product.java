package com.selfish.gene.thread.communication.problem_case;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Product {

    private int n;

    /**
     * for consumer
     * @return
     */
    public synchronized int get(){
        System.out.println("消费：" + n);
        return n;
    }

    /**
     * for producer
     * @param n
     */
    public synchronized void put(int n){
        this.n = n;
        System.out.println("生产：" + n);
    }
}
