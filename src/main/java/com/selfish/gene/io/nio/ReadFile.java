package com.selfish.gene.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Administrator on 2017/1/8.
 */
public class ReadFile {

    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\nio\\ReadFile.java";
        try (
                FileInputStream fis = new FileInputStream(path);
                FileChannel fcin = fis.getChannel()
        ) {
            ByteBuffer bbuffer = ByteBuffer.allocate(1024);
            while(fcin.read(bbuffer) != -1){
                bbuffer.flip();
                Charset charset = Charset.forName("GBK");
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cbuffer = decoder.decode(bbuffer);
                System.out.println(cbuffer);
                bbuffer.clear();
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
