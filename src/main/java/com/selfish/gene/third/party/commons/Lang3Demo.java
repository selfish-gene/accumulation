package com.selfish.gene.third.party.commons;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


/**
 * Created by Administrator on 2017/3/26.
 */
public class Lang3Demo {

    @Test
    public void testStringUtils() throws Exception {
        assertThat(StringUtils.capitalize("abc"), is("Abc"));
        assertThat(StringUtils.defaultString("abc"), is("abc"));
        assertThat(StringUtils.substringBefore("a.b.c.d", "."), is("a"));
        assertThat(StringUtils.substringAfterLast("a.b.c.d", "."), is("d"));
        assertThat(StringUtils.remove("a.b.c.d", "b."), is("a.c.d"));
        assertThat(StringUtils.reverse("a.b.c.d"), is("d.c.b.a"));
    }

    @Test
    public void testRandomStringUtils() throws Exception {
        RandomUtils randomUtils = new RandomUtils();
        String random = RandomStringUtils.random(5);
        System.out.println(random);
        // 随机大小写字母数字
        String s = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(s);
        // 随机大小写字母
        String s1 = RandomStringUtils.randomAlphabetic(10);
        System.out.println(s1);
        String s2 = RandomStringUtils.randomAscii(5);
        System.out.println(s2);
    }

    @Test
    public void testArrayUtils() throws Exception {
        int[] a = new int[]{4,9,8,54};
        int[] b = new int[]{4,5,89,24,9};
        System.out.println(Arrays.toString(ArrayUtils.add(a, 34)));
        int[] ints = ArrayUtils.addAll(a, b);
        System.out.println(Arrays.toString(ints));
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
        assertTrue(ArrayUtils.isSorted(ints));
    }

    @Test
    public void testNumberUtils() throws Exception {
        assertThat(NumberUtils.compare(5, 9), is(-1));
        assertThat(NumberUtils.toInt("", 10), is(10));
        assertThat(NumberUtils.toInt("3", 10), is(3));

    }

}
