package com.selfish.gene.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ReadFromProcess {

    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("javac");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
            String buff = null;
            while((buff = br.readLine()) != null){
                System.out.println(buff);
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}
