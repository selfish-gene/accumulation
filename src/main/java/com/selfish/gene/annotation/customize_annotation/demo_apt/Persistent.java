package com.selfish.gene.annotation.customize_annotation.demo_apt;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/6/25.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Documented
public @interface Persistent {
    String table();
}