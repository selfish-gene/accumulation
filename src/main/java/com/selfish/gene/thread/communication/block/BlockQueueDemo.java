package com.selfish.gene.thread.communication.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/18.
 */
public class BlockQueueDemo {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("a");
        bq.put("b");
        bq.add("c");// 抛出异常
        bq.put("c"); // 阻塞线程
    }
}
