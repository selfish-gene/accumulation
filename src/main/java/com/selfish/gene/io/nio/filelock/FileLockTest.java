package com.selfish.gene.io.nio.filelock;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by Administrator on 2017/1/8.
 */
public class FileLockTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\io\\nio\\channel.txt";
        try (FileChannel channel = new FileOutputStream(path).getChannel()) {
            FileLock lock = channel.tryLock();
            Thread.sleep(10000);
            lock.release();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
