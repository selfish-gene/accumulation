package com.selfish.gene.thread.communication.problem_case.plan2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Product {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int n;

    boolean flag = false;

    /**
     * for consumer
     * @return
     */
    public int get(){
        lock.lock();
        try {
            if(!flag){
                condition.await();
            }
            System.out.println("消费：" + n);
            flag = false;
            // 通知生产者生产
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return n;
    }

    /**
     * for producer
     * @param n
     */
    public void put(int n){
        lock.lock();
        try {
            if(flag){
                condition.await();
            }
            this.n = n;
            System.out.println("生产：" + n);
            flag = true;
            // 通知消费者消费
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
