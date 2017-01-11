package com.selfish.jene.io.nio.charset;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * Created by Administrator on 2017/1/8.
 */
public class CharsetTest {
    public static void main(String[] args) throws Exception {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias: map.keySet()) {
            System.out.println(alias + " -----> " + map.get(alias));
        }
    }
}
