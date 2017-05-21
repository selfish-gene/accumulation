package com.selfish.gene.thread.communication.problem_case.plan1;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Product p = new Product();
        new Thread(new Producer(p)).start();
        new Thread(new Consumer(p)).start();
    }
}
