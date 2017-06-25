package com.selfish.gene.annotation.customize_annotation.repeatable;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/6/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(FKTags.class)
public @interface FKTag {
    String name() default "selfish-gene";
    int age();
}
