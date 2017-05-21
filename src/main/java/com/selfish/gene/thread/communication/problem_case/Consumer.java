package com.selfish.gene.thread.communication.problem_case;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Consumer implements Runnable {

    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
       for (int i = 0; i < 5; i++){
           //模拟消费产品，产品数量为5
           product.get();
       }
    }
}
