package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class JoinThread extends Thread {

    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws Exception {
        new JoinThread("new_thread").start();
        for (int i = 0; i < 10; i++){
            if(i == 5){
                JoinThread jt = new JoinThread("join_thread");
                jt.start();
                //main线程调用了jt线程的join()方法，main线程，必须等jt执行结束才会向下执行
                jt.join();
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
