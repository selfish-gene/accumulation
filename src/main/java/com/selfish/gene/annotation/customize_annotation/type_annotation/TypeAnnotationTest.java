package com.selfish.gene.annotation.customize_annotation.type_annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@NotNull
public class TypeAnnotationTest implements @NotNull Serializable{
    public static void main(@NotNull String[] args) throws @NotNull Exception{
        Object object = "selfish";
        String string = (@NotNull String) object;
        System.out.println(string);
    }

    public void foo(List<@NotNull String> list){}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface NotNull{
}
