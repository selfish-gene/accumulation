package com.selfish.gene.classes.commons;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/12.
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("jva");
        assertThat(sb.toString(), is("jva"));
        sb.insert(1, "a");
        assertThat(sb.toString(), is("java"));
        sb.reverse();
        assertThat(sb.toString(), is("avaj"));
        sb.replace(0,2, "a");
        assertThat(sb.toString(), is("aaj"));
        sb.delete(0,1);
        assertThat(sb.toString(), is("aj"));
        sb.deleteCharAt(1);
        assertThat(sb.toString(), is("a"));
        assertThat(sb.length(), is(1));
        assertThat(sb.capacity(), is(16));
        sb.setCharAt(0, 'c');
        assertThat(sb.toString(), is("c"));

    }
}
