package com.my.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Administrator on 2016/12/28.
 */
public class RedirectOut {

    public static void main(String[] args) {
        String outputPath = "D:\\git\\accumulation\\src\\main\\resources\\output.txt";
        try (PrintStream ps = new PrintStream(new FileOutputStream(outputPath))){
            System.setOut(ps);
            System.out.println("String str");
            System.out.println(new RedirectOut());
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
