package com.selfish.gene.thread.communication.problem_case.plan1;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Product {

    private int n;

    boolean flag = false;

    /**
     * for consumer
     * @return
     */
    public synchronized int get(){
        try {
            if(!flag){
                wait();
            }
            System.out.println("消费：" + n);
            flag = false;
            // 通知生产者生产
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * for producer
     * @param n
     */
    public synchronized void put(int n){
        try {
            if(flag){
                wait();
            }
            this.n = n;
            System.out.println("生产：" + n);
            flag = true;
            // 通知消费者消费
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
