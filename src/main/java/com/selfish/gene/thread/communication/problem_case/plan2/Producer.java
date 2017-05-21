package com.selfish.gene.thread.communication.problem_case.plan2;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Producer implements Runnable{

    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i<5; i++){
            //模拟生产产品，产品数量为5
            product.put(i);
        }
    }
}
