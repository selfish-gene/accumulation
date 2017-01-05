package com.my.io.nio;

import java.nio.CharBuffer;

/**
 * Created by Administrator on 2017/1/6.
 */
public class BufferTest {

    public static void main(String[] args) throws Exception {
        CharBuffer charBuffer = CharBuffer.allocate(8);
        System.out.println("capacity:\t" + charBuffer.capacity());
        System.out.println("limit:\t" + charBuffer.limit());
        System.out.println("position:\t" + charBuffer.position());

        charBuffer.put('a');
        charBuffer.put('b');
        charBuffer.put('c');
        System.out.println("After adding three elements, position:\t" + charBuffer.position());

        charBuffer.flip();
        System.out.println("After the flip method is executed, limit:\t" + charBuffer.limit());
        System.out.println("position:\t" + charBuffer.position());

        System.out.println("The first element(position=0):" + charBuffer.get());
        System.out.println("After taking out the first element, position:" + charBuffer.position());

        charBuffer.clear();
        System.out.println("After the clear method is executed, limit:\t" +charBuffer.limit());
        System.out.println("After the clear method is executed, position:\t" +charBuffer.position());
        System.out.println("After the clear method is executed, the buffer is not cleared,"
                + "The third element:\t" + charBuffer.get(2));

        System.out.println("After performing an absolute read, position:\t" + charBuffer.position());
    }
}
