package com.selfish.gene.annotation.customize_annotation.demo_listener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/6/25.
 */
public class ActionListenInstaller {
    public static void processAnnotations(Object object){
        Class<?> aClass = object.getClass();
        for(Field field : aClass.getDeclaredFields()){
            field.setAccessible(true);
            //获取属性的注解类型
            ActionListenFor annotation = field.getAnnotation(ActionListenFor.class);
            try {
                // 获取属性类型
                Object o = field.get(object);
                if(annotation != null && o != null && o instanceof AbstractButton){
                    // 获取注解的成员变量
                    Class<? extends ActionListener> listener = annotation.listener();
                    ActionListener actionListener = listener.newInstance();
                    AbstractButton abstractButton = (AbstractButton) o;
                    abstractButton.addActionListener(actionListener);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
