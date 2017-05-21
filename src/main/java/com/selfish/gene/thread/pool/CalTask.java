package com.selfish.gene.thread.pool;

import java.util.concurrent.RecursiveTask;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CalTask extends RecursiveTask<Integer> {

    //每个“小任务”最多只累加20个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;

    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当end与start之间的差小于THRESHOLD，直接累加
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++){
                sum += arr[i];
            }
            return sum;
        } else {
            //当end与start之间的差大于THRESHOLD．即要累加的数超过20个时,将大任务分解成两个“小任务”
            int middle = (start + end)/2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            //把两个任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
