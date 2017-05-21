package com.selfish.gene.thread.communication.syn;

/**
 * Created by Administrator on 2017/5/18.
 */
public class DrawDemo {
    public static void main(String[] args) throws Exception {
        Account account = new Account("abc", 600);

        new DrawThread("drawA", account, 600).start();
        new DepositThread("depositA", account, 600).start();
        new DepositThread("depositB", account, 600).start();
        new DepositThread("depositC", account, 600).start();
        // 最终程序阻塞，因为存钱线程总共执行15次，而取钱线程执行只有5次
    }
}
