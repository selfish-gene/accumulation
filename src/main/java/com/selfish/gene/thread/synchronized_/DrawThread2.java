package com.selfish.gene.thread.synchronized_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class DrawThread2 extends Thread{

    private Account2 account;

    private double drawAccount;

    public DrawThread2(String name, Account2 account, double drawAccount){
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }

    @Override
    public void run() {
        account.draw(drawAccount);
    }

    public static void main(String[] args) throws Exception {
        Account2 account = new Account2("123", 1000);
        new DrawThread2("A", account,600).start();
        new DrawThread2("B", account,600).start();
    }
}
