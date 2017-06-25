package com.selfish.gene.annotation.customize_annotation.repeatable;

import com.selfish.gene.annotation.customize_annotation.repeatable.FKTag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/6/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FKTags {
    FKTag[] value();
}
