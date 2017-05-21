package com.selfish.gene.thread.pool;

import java.util.concurrent.RecursiveAction;

/**
 * Created by Administrator on 2017/5/20.
 */
public class PrintTask extends RecursiveAction {

    //每个“小任务”最多只打印50个数
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < THRESHOLD){
            for (int i = start; i < end; i++){
                System.out.println(Thread.currentThread().getName() + "的i值为：" + i);
            }
        } else{
            //当end与start之间的差大于THRESHOLD．即要打印的数超过50个时,将大任务分解成两个“小任务”
            int middle = (start + end)/2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }
    }
}
