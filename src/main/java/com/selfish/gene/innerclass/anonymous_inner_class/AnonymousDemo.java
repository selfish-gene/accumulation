package com.selfish.gene.innerclass.anonymous_inner_class;

/**
 * Created by Administrator on 2017/6/11.
 */
public class AnonymousDemo {
    public void test(Product p){
        System.out.println("购买" + p.getName() + "花费了" + p.getPrice() + "元");
    }

    public static void main(String[] args) throws Exception {
        AnonymousDemo a = new AnonymousDemo();
        a.test(new Product() {
            @Override
            public double getPrice() {
                return 15567.2;
            }

            @Override
            public String getName() {
                return "苹果电脑";
            }
        });
    }
}

interface Product {
    double getPrice();
    String getName();
}
