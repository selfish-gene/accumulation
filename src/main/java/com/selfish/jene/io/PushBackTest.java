package com.selfish.jene.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

/**
 * Created by Administrator on 2016/12/28.
 */
public class PushBackTest {

    public static void main(String[] args){
        String filePath = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\PushBackTest.java";
        try (PushbackReader pr = new PushbackReader(new FileReader(filePath), 64))
        {
            char[] chars = new char[32];
            String lastContent = "";
            int hasRead = 0;
            while((hasRead = pr.read(chars)) > 0){
                String content = new String(chars, 0, hasRead);
                int targetIndex = 0;
                if((targetIndex = (lastContent + content).indexOf("new PushbackReader")) > 0){
                    pr.unread((lastContent + content).toCharArray());
                    if(targetIndex > 32){
                        chars = new char[targetIndex];
                    }
                    pr.read(chars, 0, targetIndex);
                    System.out.println(new String(chars, 0, targetIndex));
                    System.exit(0);
                }else{
                    System.out.println(lastContent);
                    lastContent = content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
