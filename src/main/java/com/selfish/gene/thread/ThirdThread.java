package com.selfish.gene.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/5/17.
 */
public class ThirdThread {
    public static void main(String[] args) throws Exception {
        ThirdThread rt = new ThirdThread();
        // 创建Callable<Integer>对象
        Callable<Integer> integerCallable = () -> {
            int i = 0;
            for (; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            return i;
        };
        FutureTask<Integer> task = new FutureTask<>(integerCallable);
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                new Thread(task, "thread_with_returnValue").start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
