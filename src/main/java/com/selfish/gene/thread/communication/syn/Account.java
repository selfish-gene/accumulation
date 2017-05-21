package com.selfish.gene.thread.communication.syn;

/**
 * Created by Administrator on 2017/5/18.
 */
public class Account {
    private String accountNo;
    private double balance;
    // 标记账户是否有存款，true：有，false：没有
    private boolean flag = true;

    public Account() {
    }

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }
    //账户余额不允许修改，只提供getter方法
    public double getBalance() {
        return balance;
    }
    // 取钱
    public synchronized void draw(double drawAmount){
        try {
            // false表示账户中没有存款
            if(!flag){
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " drawAmount:" + drawAmount);
                balance -= drawAmount;
                System.out.println("balance:" + balance);
                // 钱被取走之后，flag设置为false
                flag = false;
                // 唤醒等待存钱的线程
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 存钱
    public synchronized void deposit(double drawAmount){
        // flag为true表示账户有余额，等待有人取钱
        try {
            if(flag){
                wait();
            } else {
                System.out.println(Thread.currentThread().getName() + " depositAmount:" + drawAmount);
                balance += drawAmount;
                System.out.println("balance:" + balance);
                // 存钱之后将flag设置为true
                flag = true;
                // 唤醒等待取钱的线程
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
