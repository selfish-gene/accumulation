package com.selfish.gene.thread.pool;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Sum {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[100];
        Random r = new Random();
        int total = 0;
        for (int i = 0; i < 100; i++){
            int tmp = r.nextInt(20);
            arr[i] = tmp;
            total += arr[i];
        }
        System.out.println(total);

        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        System.out.println(future.get());
        pool.shutdown();
    }
}
