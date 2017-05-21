package com.selfish.gene.thread.synchronized_;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/5/17.
 */
public class Account3 {

    private final ReentrantLock lock = new ReentrantLock();
    private String accountNo;
    private double balance;

    public Account3() {
    }

    public Account3(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return this.balance;
    }

    // 下面两个方法根据accountNo来重写hashCode()和equals()方法
    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj != null && obj.getClass() == Account3.class){
            Account3 target = (Account3) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }

    public void draw(double drawAccount){
        lock.lock();
        try {
            if(balance >= drawAccount){
                System.out.println(Thread.currentThread().getName() + "取钱成功，数量为：" + drawAccount);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= drawAccount;
                System.out.println("\t余额为:" + this.getBalance());
            } else {
                System.out.println(Thread.currentThread().getName() +"余额不足");
            }
        } finally {
            lock.unlock();
        }
    }
}
