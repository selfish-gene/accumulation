package com.selfish.gene.thread.synchronized_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class DrawThread extends Thread{

    private Account account;

    private double drawAccount;

    public DrawThread(String name, Account account, double drawAccount){
        super(name);
        this.account = account;
        this.drawAccount = drawAccount;
    }

    @Override
    public void run() {
        synchronized (account) {
            if(account.getBalance() >= drawAccount){
                System.out.println("取钱成功，数量为：" + drawAccount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - drawAccount);
                System.out.println("\t余额为:" + account.getBalance());
            } else {
                System.out.println("余额不足");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Account account = new Account("123", 1000);
        new DrawThread("A", account,600).start();
        new DrawThread("B", account,600).start();
    }
}
