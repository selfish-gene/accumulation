package com.my.io;

import java.io.PrintStream;

/**
 * Created by Administrator on 2016/12/29.
 */
public class WriteToProcess {

    public static void main(String[] args) throws Exception {
        // TODO Fail to write to ReadStandard. Why?
        Process p = Runtime.getRuntime().exec("java ReadStandard");
        try (PrintStream ps = new PrintStream(p.getOutputStream())) {
            ps.println("普通字符串");
            ps.println(new WriteToProcess());
        }
    }

}
