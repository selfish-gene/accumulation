package com.selfish.gene.thread.communication.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/18.
 */
public class BlockingQueueDemo2 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);

//        new Producer(bq).start();
//        new Producer(bq).start();
        Producer producer = new Producer(bq);
        producer.setName("producer");
        producer.start();

        Consumer consumer = new Consumer(bq);
        consumer.setName("consumer");
        consumer.start();

    }
}
