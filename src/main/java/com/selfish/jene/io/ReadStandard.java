package com.selfish.jene.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/29.
 */
public class ReadStandard {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\resources\\readStandard.txt";
        try (Scanner sc = new Scanner(System.in);
                PrintStream ps = new PrintStream(new FileOutputStream(filePath))) {
            sc.useDelimiter("\n");
            while(sc.hasNext()){
                ps.println("The contents of the keyboard input are:" + sc.next());
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
