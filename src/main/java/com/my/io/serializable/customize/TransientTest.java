package com.my.io.serializable.customize;

import java.io.*;

/**
 * Created by Administrator on 2017/1/5.
 */
public class TransientTest {

    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\transient.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
        ) {
            Person p = new Person("孙悟空", 500);
            oos.writeObject(p);
            Person readP = (Person)ois.readObject();
            System.out.println(readP.getAge());// The expectation is 0
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}
