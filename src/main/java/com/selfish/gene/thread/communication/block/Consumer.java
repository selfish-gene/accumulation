package com.selfish.gene.thread.communication.block;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/18.
 */
public class Consumer extends Thread {

    private BlockingQueue<String> bq;

    public Consumer(BlockingQueue<String> bq){
        this.bq = bq;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println(getName() + " 消费者准备消费集合元素");
            try {
                Thread.sleep(200);
                // 尝试取出元素，如果队列已空，则线程被阻塞
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + " 消费完成：" + bq);
        }
    }
}
