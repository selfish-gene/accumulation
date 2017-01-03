package com.my.io.randomAccessFile;

import java.io.*;

/**
 * Created by Administrator on 2017/1/3.
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\randomAccessFile\\RandomAccessFileTest.java";
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            System.out.println("The initial location of the file:\t" + raf.getFilePointer());
            raf.seek(300);
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            while((hasRead = raf.read(bytes)) > 0){
                System.out.println(new String(bytes, 0, hasRead));
            }
        }catch (IOException io){
            io.printStackTrace();
        }

        String outputPath = "D:\\git\\accumulation\\src\\main\\resources\\output.txt";
        appendContent(outputPath, "rw");

        String insertPath = "D:\\git\\accumulation\\src\\main\\resources\\insertContent.txt";
        insertContent(insertPath, 5, "The inserted content\n");
    }

    public static void appendContent(String filePath, String mode){
        try (RandomAccessFile raf = new RandomAccessFile(filePath, mode)){
            raf.seek(raf.length());
            raf.write("The append content\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertContent(String filePath, long postion, String insertContent) throws IOException {
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();
        try (
                RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
                FileInputStream fis = new FileInputStream(tmp);
                FileOutputStream fos = new FileOutputStream(tmp)
        ) {
            //Save the content after the insertion point
            raf.seek(postion);
            byte[] bytes = new byte[64];
            int hasRead = 0;
            while ((hasRead = raf.read(bytes)) > 0){
                fos.write(bytes, 0, hasRead);
            }
            //Insert content at the insertion point
            raf.seek(postion);
            raf.write(insertContent.getBytes());
            //Append the saved content
            while((hasRead = fis.read(bytes)) > 0){
                raf.write(bytes, 0, hasRead);
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
