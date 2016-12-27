package com.my.io;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by Administrator on 2016/12/28.
 */
public class StringNodeTest {

    public static void main(String[] args) throws Exception{
        String src = "My heart is in the work";
        getStringReader(src);
    }

    private static void getStringReader(String str) {
        char[] chars = new char[32];
        int hasRead = 0;
        try (StringReader sr = new StringReader(str)) {
            while((hasRead = sr.read(chars)) > 0){
                System.out.println(getStringWriter(new String(chars, 0, hasRead)));
            }
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private static String getStringWriter(String str){
        String hasWrite = null;
        try (StringWriter sw = new StringWriter()) {
            sw.write(str);
            hasWrite = sw.toString();
        }catch (IOException io){
            io.printStackTrace();
        }
        return hasWrite;
    }


}
