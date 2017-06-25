package com.selfish.gene.annotation.customize_annotation.repeatable;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/25.
 */
@FKTag(age = 20)
@FKTag(name = "Edam", age = 20)
public class FKTagTest {
    public static void main(String[] args) {
        Class<FKTagTest> fkTagTestClass = FKTagTest.class;
        Arrays.stream(fkTagTestClass.getAnnotationsByType(FKTag.class)).forEach(fkTag -> System.out.println(fkTag.name() + "-->" + fkTag.age()));
        FKTags container = fkTagTestClass.getAnnotation(FKTags.class);
        System.out.println(container);
    }
}
