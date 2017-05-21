package com.selfish.gene.thread.thread.group;

/**
 * Created by Administrator on 2017/5/20.
 */
public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(getName() + " i:" + i);
        }
    }
}
