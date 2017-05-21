package com.selfish.gene.thread.threadlocal;

/**
 * Created by Administrator on 2017/5/20.
 */
public class MyDemo extends Thread{
    private Account account;

    public MyDemo(Account account, String name){
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++){
            if(i == 6){
                account.setName(getName());
            }
            String accountName = account.getName();
            System.out.println(accountName + " 账户的i值：" + i);
        }
    }

    public static void main(String[] args) throws Exception {
        Account account = new Account("init");
        /*虽然两个线程共享同一个账户，即只有一个账户名
        但由于账户名是ThreadLocal类型的，所以每个线程
        都完全拥有各自的账户名副本，因此在i==6之后．将看到两个
        线程访问同一个账户时出现不同的账户名
        */
        new MyDemo(account, "A").start();
        new MyDemo(account, "B").start();
    }
}
