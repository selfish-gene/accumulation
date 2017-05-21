package com.selfish.gene.thread;

/**
 * Created by Administrator on 2017/5/17.
 */
public class StartDead extends Thread {
    private int i;

    public void run(){
        for(;i<10;i++){
            System.out.println(getName() + " " + i);
        }
    }

    //运行程序，将引发IllegalThreadStateException异常，这表明处于死亡状态的线程无法再次运行了
    public static void main(String[] args) throws Exception {
        StartDead startDead = new StartDead();
        for(int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i == 5){
                startDead.start();
                System.out.println(startDead.isAlive());
            }
            // 只有当线程处于新建、死亡两种状态时isAlive()方法返回false。
            // 当i > 5，则该线程肯定已经启动过了，如果startDead.isAlive()为假时，
            // 那只能是死亡状态了。
            if(i > 5 && !startDead.isAlive()){
                startDead.start();
            }
        }
    }
}
