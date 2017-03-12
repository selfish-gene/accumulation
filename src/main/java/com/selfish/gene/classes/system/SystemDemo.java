package com.selfish.gene.classes.system;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/12.
 */
public class SystemDemo {
    public static void main(String[] args) throws Exception{
        // 获取系统所有的环境变量
        Map<String, String> env = System.getenv();
        env.forEach((key, value) -> System.out.println(key + " --> " + value));
        // 获取指定环境变量的值
        System.out.println(env.get("JAVA_HOME"));
        // 获取所有的系统属性
        Properties props =System.getProperties();
        // 将所有系统属性保存到props.txt文件中
        props.store(new FileOutputStream(SystemDemo.class.getResource("/").getPath() + "props.txt"), "System properties");
        // 输出特定的系统属性
        System.out.println(System.getProperty("os.name"));
    }
}
