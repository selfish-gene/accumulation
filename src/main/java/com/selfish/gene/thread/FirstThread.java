package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class FirstThread extends Thread{

    private int i;

    @Override
    public void run(){
        for(; i < 5; i++){
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                new FirstThread().start();
                new FirstThread().start();
                Thread.currentThread().sleep(100);
            }
        }
    }
}
