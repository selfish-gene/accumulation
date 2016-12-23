package com.my.io;


import java.io.*;

/**
 * Created by Administrator on 2016/12/22.
 */
public class IOClasses {

    private static final PrintStream out = System.out;

    public static void main(String[] args) throws Exception{
        // TODO  How to obtain the current class path in Java?
        //The location of the binary file   /D:/git/accumulation/target/classes/com/my/io/
        String fileLocation = IOClasses.class.getResource("").getPath();
        //The location of the binary directory   /D:/git/accumulation/target/classes/
        String classDirLocation = IOClasses.class.getResource("/").getPath();

        String src = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\IOClasses.java";
        String dest = "D:\\git\\accumulation\\src\\main\\resources\\newFile.txt";
        //getFileInputStream(src);
        //getFileRead(src);
        //readAndWriteByStream(src, dest);

        String filePath = "D:\\git\\accumulation\\src\\main\\resources\\poem.txt";
        String poem = "锦瑟-李商隐\r\n" +
                "锦瑟无端五十弦，一弦一柱思华年。\r\n" +
                "庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n" +
                "沧海月明珠有泪，蓝田日暖玉生烟。\r\n" +
                "此情可待成追忆，只是当时已惘然。 \r\n";
        //writeByFileWrite(filePath,poem);
        //getPrintStream("D:\\git\\accumulation\\src\\main\\resources\\test.txt");

        //getStringReader(poem);
        getStringWriter(poem);
    }

    private static void getFileInputStream(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        byte[] bytes = new byte[1024];
        int hasRead = 0;
        while((hasRead = in.read(bytes)) > 0){
            out.println(new String(bytes, 0, hasRead));// The bytes to be decoded into characters
        }
        in.close();
    }

    private static void getFileRead(String filePath){
        try (
                FileReader reader = new FileReader(filePath)
        ){
            char[] chars = new char[32];
            int hasRead = 0;
            while((hasRead = reader.read(chars)) > 0){
                out.print(new String(chars, 0, hasRead));
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private static void readAndWriteByStream(String src, String dest){
        try (
                FileInputStream in = new FileInputStream(src);
                FileOutputStream out = new FileOutputStream(dest)
                ){
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            while((hasRead = in.read(bytes)) > 0){
                out.write(bytes, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeByFileWrite(String filePath, String content){
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    private static void getPrintStream(String filePath){
        try (PrintStream out = new PrintStream(new FileOutputStream(filePath))){
            out.println();
            out.println(IOClasses.class.getSimpleName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getStringReader(String str){
        char[] chars = new char[1024];
        int hasRead = 0;
        try (StringReader sr = new StringReader(str)) {
            while((hasRead = sr.read(chars)) > 0){
                out.println(new String(chars, 0 ,hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void getStringWriter(String str){
        try (StringWriter sw = new StringWriter()) {
            sw.write(str);
            out.println(sw.toString());
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}
