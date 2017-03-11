package com.selfish.gene.collection.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/11.
 */
public class PropertiesDemo {
    public static void main(String[] args) throws Exception {
        // 向ini文件中添加属性
        Properties properties = new Properties();
        properties.setProperty("username", "selfish_gene");
        properties.setProperty("password", "123456");
        String path = PropertiesDemo.class.getResource("/").getPath() + "prop.ini";
        properties.store(new FileOutputStream(path), "comment line");

        // 读取ini文件的属性
        Properties properties1 = new Properties();
        properties1.setProperty("gender", "male");
        properties1.load(new FileInputStream(path));
        System.out.println(properties1);
    }
}
