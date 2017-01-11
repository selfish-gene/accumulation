package com.selfish.jene.io.serializable.customize;

import java.io.*;

/**
 * Created by Administrator on 2017/1/5.
 */
public class ResolveTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\serializable\\resolve.txt";
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))
        ) {
            oos.writeObject(Orientation.HORIZONTAL);
            Orientation ori = (Orientation)ois.readObject();
            System.out.println(ori == Orientation.HORIZONTAL);
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
