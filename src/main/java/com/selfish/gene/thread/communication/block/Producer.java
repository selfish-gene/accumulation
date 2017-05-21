package com.selfish.gene.thread.communication.block;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2017/5/18.
 */
public class Producer extends Thread {

    private BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq){
        this.bq = bq;
    }

    @Override
    public void run() {
       String[] strArr = new String[]{"Java", "Maven", "Git"};
       for(int i = 0; i < 10; i++){
           System.out.println(getName() + " 生产者准备生产集合元素");
           try {
               Thread.sleep(200);
               // 尝试放入元素，如果队列已满，则线程被阻塞
               bq.put(strArr[i % 3]);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(getName() + " 生产完成：" + bq);
       }
    }
}
