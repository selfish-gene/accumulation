package com.selfish.gene.annotation.customize_annotation;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/25.
 */

public class Test {

    @MyTag
    public void info()
    {

    }

    public static void main(String[] args) throws Exception {
//        Arrays.stream(Test.class.getMethod("info").getAnnotations()).forEach(name -> System.out.println(name));
        Arrays.stream(Test.class.getMethod("info").getAnnotations()).forEach(anno -> {
            if(anno instanceof MyTag){
                System.out.println(((MyTag) anno).name());
                System.out.println(((MyTag) anno).age());
            }
        });

    }
}
