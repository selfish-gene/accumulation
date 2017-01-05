package com.my.io.serializable.externalizable;

import java.io.*;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ExternalizableTest {

    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\externalizable\\externalizable.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
        ) {
            Person p = new Person("Ellen", 500);
            oos.writeObject(p);
            Person readP = (Person)ois.readObject();
            System.out.println(readP.getName());
            System.out.println(readP.getAge());
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
