package com.selfish.gene.classes.commons;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Administrator on 2017/3/12.
 */
public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextBoolean());

        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));
        // 生成0.0~1.0之间的伪随机double数
        System.out.println(random.nextDouble());
        // 生成0.0~1.0之间的伪随机float数
        System.out.println(random.nextFloat());
        // 生成平均值是 0.0，标准差是 1.0的伪高斯数
        System.out.println(random.nextGaussian());
        // 生成平均值是 0.0，标准差是 1.0的伪高斯数
        System.out.println(random.nextInt());
        // 生成0~26之间的伪随机整数
        System.out.println(random.nextInt(26));
        // 生成一个处于long整数取值范围的伪随机整数
        System.out.println(random.nextLong());

        Random r1 = new Random(50);
        System.out.println("第一个种子为50的Random对象");
        System.out.println("r1.nextBoolean():\t" + r1.nextBoolean());
        System.out.println("r1.nextInt():\t\t" + r1.nextInt());
        System.out.println("r1.nextDouble():\t" + r1.nextDouble());
        System.out.println("r1.nextGaussian():\t" + r1.nextGaussian());
        System.out.println("---------------------------");
        Random r2 = new Random(50);
        System.out.println("第二个种子为50的Random对象");
        System.out.println("r2.nextBoolean():\t" + r2.nextBoolean());
        System.out.println("r2.nextInt():\t\t" + r2.nextInt());
        System.out.println("r2.nextDouble():\t" + r2.nextDouble());
        System.out.println("r2.nextGaussian():\t" + r2.nextGaussian());
        System.out.println("---------------------------");
        Random r3 = new Random(100);
        System.out.println("种子为100的Random对象");
        System.out.println("r3.nextBoolean():\t" + r3.nextBoolean());
        System.out.println("r3.nextInt():\t\t" + r3.nextInt());
        System.out.println("r3.nextDouble():\t" + r3.nextDouble());
        System.out.println("r3.nextGaussian():\t" + r3.nextGaussian());

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom.nextInt(4, 20));
        System.out.println( threadLocalRandom.nextDouble(2.0, 10.0));

    }
}
