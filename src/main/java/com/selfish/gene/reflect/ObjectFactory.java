package com.selfish.gene.reflect;

import javax.swing.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/17.
 */
public class ObjectFactory {
    public static void main(String[] args) {
        // 不使用泛型，获取实例后需要强制转换，随便编译期没报错，但运行期会报错
        Date date = (Date) getInstance("java.util.Date");
//        JFrame jFrame = (JFrame) getInstance("java.util.Date");

        Date date2 = getInstance2(Date.class);
        JFrame jFrame2= getInstance2(JFrame.class);

    }

    public static Object getInstance(String className){
        try {
            Class clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T>  T getInstance2(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
