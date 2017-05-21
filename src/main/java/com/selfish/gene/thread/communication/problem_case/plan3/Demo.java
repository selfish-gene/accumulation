package com.selfish.gene.thread.communication.problem_case.plan3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(1);
        Product p = new Product(bq);
        new Thread(new Producer(p)).start();
        new Thread(new Consumer(p)).start();
    }
}
