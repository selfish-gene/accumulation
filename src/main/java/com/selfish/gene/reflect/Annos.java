package com.selfish.gene.reflect;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2017/3/13.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Annos {
    Anno[] value();
}
