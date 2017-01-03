package com.my.io.serializable;

import java.io.*;

/**
 * Created by Administrator on 2017/1/4.
 */
public class SerializableMutable {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\mutable.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))
        ) {
            Person p = new Person("Ellen", 500);
            oos.writeObject(p);
            p.setName("Adam");
            oos.writeObject(p);
            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();
            System.out.println(p1 == p2);
            System.out.println(p2.getName());
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
