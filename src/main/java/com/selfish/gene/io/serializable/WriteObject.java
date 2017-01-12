package com.selfish.gene.io.serializable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2017/1/4.
 */
public class WriteObject {

    public static void main(String[] args) throws Exception {
        String filePath ="D:\\git\\accumulation\\src\\main\\resources\\serializable\\object.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            Person p = new Person("anlei", 20);
            oos.writeObject(p);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
