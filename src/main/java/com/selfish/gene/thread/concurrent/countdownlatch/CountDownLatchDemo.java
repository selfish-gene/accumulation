package com.selfish.gene.thread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CountDownLatchDemo implements Runnable{

    private CountDownLatch start;
    private CountDownLatch end;

    public CountDownLatchDemo(CountDownLatch start, CountDownLatch end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            start.await();
            for (int i = getEndCount(); i > 0; i--) {
                System.out.println("结束倒计时：" + i + "s");
                Thread.currentThread().sleep(1000);
                end.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getBeginCount(){
        return 5;
    }

    private static int getEndCount(){
        return 10;
    }

    public static void main(String[] args) throws Exception {
        // 当计数为0时启动线程，否则线程挂起
        CountDownLatch start = new CountDownLatch(getBeginCount());
        CountDownLatch end = new CountDownLatch(getEndCount());

        new Thread(new CountDownLatchDemo(start, end)).start();
        for (int i = getBeginCount(); i > 0; i--){
            System.out.println("开始倒计时：" + i + "s");
            Thread.currentThread().sleep(1000);
            start.countDown();
        }
        System.out.println("begin something-----------------------------");
        end.await();
        System.out.println("the end");
    }
}
