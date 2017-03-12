package com.selfish.gene.classes.system;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/12.
 */
public class RuntimeDemo {

    public static void main(String[] args) throws IOException {
        Runtime rt = Runtime.getRuntime();
        System.out.println("处理器数量：" + rt.availableProcessors());
        System.out.println("空闲内存数：" + rt.freeMemory());
        System.out.println("总内存数：" + rt.totalMemory());
        System.out.println("可用最大内存数：" + rt.maxMemory());

        // 运行记事本程序
        rt.exec("notepad.exe");
    }
}
