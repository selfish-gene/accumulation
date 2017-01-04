package com.my.io.serializable.customize;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ReplaceTest {

    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\replace.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
        ) {
            PersonCustomizeTwo ptwo = new PersonCustomizeTwo("sun",500);
            oos.writeObject(ptwo);
            ArrayList list = (ArrayList)ois.readObject();
            System.out.println(list);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
