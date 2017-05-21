package com.selfish.gene.thread.communication.problem_case;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        // 过度消费与过度生产
        Product p = new Product();
        new Thread(new Producer(p)).start();
        new Thread(new Consumer(p)).start();
    }
}
