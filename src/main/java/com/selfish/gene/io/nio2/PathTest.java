package com.selfish.gene.io.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/1/8.
 */
public class PathTest {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\nio2\\PathTest.java");
        System.out.println("Path contains the number of paths：\t" + path.getNameCount());
        System.out.println("The root path of the path：\t" + path.getRoot());

        System.out.println("The absolute path to the path：\t" + path.toAbsolutePath());
        System.out.println(path.toAbsolutePath().getRoot());
        System.out.println(path.toAbsolutePath().getNameCount());

        Path path2 = Paths.get("g:", "program", "code");
        System.out.println(path2);
    }
}
