package com.my.io.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by Administrator on 2017/1/8.
 */
public class CharsetTransform {
    public static void main(String[] args) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.put("Ellen");
        charBuffer.flip();

        ByteBuffer bytebuffer = encoder.encode(charBuffer);
        for (int i = 0; i < bytebuffer.capacity(); i++){
            System.out.println(bytebuffer.get(i) + "");
        }
        System.out.println("\n"+ decoder.decode(bytebuffer));

        // TODO: 2017/1/8 Why do not have anything
        //System.out.println("\n" + charset.decode(charset.encode(charBuffer)));
    }
}
