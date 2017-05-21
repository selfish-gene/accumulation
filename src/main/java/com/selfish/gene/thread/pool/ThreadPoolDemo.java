package com.selfish.gene.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2017/5/20.
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws Exception {
        //创建一个具有固定线程数(6)的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Runnable target = () -> {
            for (int i = 0; i < 5; i++){
                System.out.println(Thread.currentThread().getName() + " 的i值为：" + i);
            }
        };
        //向池中添加2个线程
        Future<Integer> future = pool.submit(target, 1);
        System.out.println(future.get());
        pool.submit(target);
        //关闭线程池
        pool.shutdown();
    }
}
