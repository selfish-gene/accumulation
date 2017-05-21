package com.selfish.gene.thread.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/20.
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(0,300));
        pool.awaitTermination(5, TimeUnit.SECONDS);
        pool.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
