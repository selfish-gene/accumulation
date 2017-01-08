package com.my.io.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2017/1/8.
 */
public class RandomFileChannelTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\resources\\io\\nio\\channel.txt";
        File f = new File(path);
        try (
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                FileChannel randomChannel = raf.getChannel()
        ) {
            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            randomChannel.position(f.length());
            randomChannel.write(buffer);
        }
    }
}
