package com.selfish.gene.io.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by Administrator on 2017/1/8.
 */
public class FileVisitorTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io";
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                System.out.println("The file being accessed is :" + file);
                if(file.endsWith("FileVisitorTest.java")){
                    System.out.println("--Find the target file--");
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("The dir being accessed is :" + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
