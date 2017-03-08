package com.selfish.gene.collection.set.treeset;

import java.util.TreeSet;

/**
 * Created by Administrator on 2017/3/9.
 */
public class TreeSetDemo4 {

    public static void main(String[] args) {
        TreeSet ts = new TreeSet( (o1, o2) ->
        {
            M m1 = (M) o1;
            M m2 = (M) o2;
            // 根据M对象的age属性来决定大小，age越大，M对象反而越小
            return m1.age > m2.age ? -1
                    : m1.age < m2.age ? 1 :0;
        });
        ts.add(new M(5));
        ts.add(new M(-2));
        ts.add(new M(9));
        System.out.println(ts);
    }


    static class M {
        int age;

        public M(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "M{" +
                    "age=" + age +
                    '}';
        }
    }
}
