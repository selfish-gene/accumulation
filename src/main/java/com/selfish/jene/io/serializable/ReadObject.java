package com.selfish.jene.io.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Administrator on 2017/1/4.
 */
public class ReadObject {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\object.txt";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Person p = (Person) ois.readObject();
            System.out.println("name:\t" + p.getName() + "\nage:\t" + p.getAge());
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
