package com.selfish.gene.thread.communication.syn;

/**
 * Created by Administrator on 2017/5/18.
 */
public class DepositThread extends Thread {

    private Account account;
    private double depositAmount;

    public DepositThread(String name, Account account, double depositAmount){
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            account.deposit(depositAmount);
        }
    }
}
