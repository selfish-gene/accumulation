package com.selfish.gene.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/12/28.
 */
public class RedirectIn {

    public static void main(String[] args) {
        String filePath = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\RedirectIn.java";
        try (FileInputStream fis = new FileInputStream(filePath)){
            System.setIn(fis);
            Scanner sc = new Scanner(System.in);
            //sc.useDelimiter("\n");
            while(sc.hasNext()){
                System.out.println("The contents of the keyboard input are:" + sc.next());
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
