package com.selfish.gene.lambda;

import com.selfish.gene.lambda.function.Displayable;

/**
 * Created by Administrator on 2017/3/12.
 */
public class LambdaAndInner {

    private int age = 20;
    private static String name = "selfish_gene";
    public void test(){
        String skill = "Java";
        Displayable dis = () -> {
            System.out.println("skill is :" + skill);
            System.out.println("age is : " + age);
            System.out.println("name is : " + name);
            // Lambda表示代码块中不能调用接口中定义的默认方法
//            System.out.println(dis.add(3, 5));
        };
        dis.display();
        System.out.println(dis.add(3, 5));
    }

    public static void main(String[] args) {
        LambdaAndInner lai = new LambdaAndInner();
        lai.test();
    }
}
