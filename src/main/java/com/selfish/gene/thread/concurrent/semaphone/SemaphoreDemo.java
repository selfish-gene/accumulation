package com.selfish.gene.thread.concurrent.semaphone;

import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/5/20.
 */
public class SemaphoreDemo {
    private Semaphore consumer = new Semaphore(0);
    private Semaphore producer = new Semaphore(1);
    // 模拟产品
    int n;

    // 消费者
    public int get(){
        try {
            // 获取消费许可，没有则线程挂起，代码不往下执行；有则消费一个许可，许可数量-1
            consumer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费:" + n);
        // 每当消费者消费后，给生产者释放一个许可
        producer.release();
        return n;
    }

    // 生产者
    public void put(int n){
        try {
            // 获取生产许可，没有则线程挂起，代码不往下执行；有则消费一个许可，许可数量-1
            producer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.n = n;
        // 每当生产者生产后，给消费者释放一个许可
        consumer.release();
        System.out.println("生产:" + n);
    }

    public static void main(String[] args) throws Exception {
        SemaphoreDemo q = new SemaphoreDemo();
        for (int i = 0; i < 5; i++){
            q.put(i);
            q.get();
        }
    }
}
