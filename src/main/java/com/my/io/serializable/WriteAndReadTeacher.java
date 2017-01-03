package com.my.io.serializable;

import java.io.*;

/**
 * Created by Administrator on 2017/1/4.
 */
public class WriteAndReadTeacher {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\object.txt";
        writeTeacher(filePath);
        readTeacher(filePath);

    }

    public static void writeTeacher(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            Person p = new Person("孙悟空", 500);
            Teacher t1 = new Teacher("菩萨", p);
            Teacher t2 = new Teacher("菩提祖师", p);
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(p);
            oos.writeObject(t2);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void readTeacher(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Teacher t1 = (Teacher) ois.readObject();
            Teacher t2 = (Teacher) ois.readObject();
            Person p = (Person) ois.readObject();
            Teacher t3 = (Teacher) ois.readObject();
            System.out.println(t1.getStudent() == p);
            System.out.println(t2.getStudent() == p);
            System.out.println(t2 == t3);
        } catch (IOException io) {
            io.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
