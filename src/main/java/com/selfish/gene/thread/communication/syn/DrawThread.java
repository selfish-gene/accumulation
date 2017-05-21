package com.selfish.gene.thread.communication.syn;

/**
 * Created by Administrator on 2017/5/18.
 */
public class DrawThread extends Thread{

    private Account account;
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount){
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            account.draw(drawAmount);
        }
    }
}
