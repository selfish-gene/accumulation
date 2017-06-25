package com.selfish.gene.annotation.customize_annotation.demo_testable;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/25.
 */
public class ProcessorTest {
    public static void process(Class clazz){
        int passed = 0;
        int failed = 0;
        for(Method method : clazz.getMethods()){
            if(method.isAnnotationPresent(Testable.class)){
                try {
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    System.out.println("method" + method + "invoke failed:" + e.getCause());
                    failed++;
                }
            }
        }
        System.out.println("sun = " + (passed + failed));
        System.out.println("success = " + passed);
        System.out.println("fail = " + failed);
    }

    public static void main(String[] args) {
        ProcessorTest.process(MyTest.class);
    }
}
