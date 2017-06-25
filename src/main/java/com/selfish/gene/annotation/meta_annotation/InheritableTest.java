package com.selfish.gene.annotation.meta_annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/25.
 */
public class InheritableTest extends Base
{
    public static void main(String[] args) {
//        System.out.println(InheritableTest.class.isAnnotationPresent(Inheritable.class));
        Inheritable inheritable = InheritableTest.class.getAnnotation(Inheritable.class);
        Class<? extends Annotation> aClass = inheritable.annotationType();
        Arrays.stream(aClass.getMethods()).forEach(name -> System.out.println(name));
    }
}

@Inheritable
class Base
{

}
