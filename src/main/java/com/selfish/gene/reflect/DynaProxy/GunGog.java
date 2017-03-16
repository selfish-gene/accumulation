package com.selfish.gene.reflect.DynaProxy;

/**
 * Created by Administrator on 2017/3/16.
 */
public class GunGog implements Dog {
    @Override
    public void info() {
        System.out.println("a gun dog");
    }

    @Override
    public void run() {
        System.out.println("run");
    }
}
