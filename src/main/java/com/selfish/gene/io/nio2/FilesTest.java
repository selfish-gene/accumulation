package com.selfish.gene.io.nio2;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */
public class FilesTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\nio2\\FilesTest.java";
        String outPath = "D:\\git\\accumulation\\src\\main\\resources\\io\\nio\\nio2\\files.txt";

        Files.copy(Paths.get(path), new FileOutputStream(outPath));
        System.out.println(Files.isHidden(Paths.get(path)));

        List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
        System.out.println(lines);
        System.out.println(Files.size(Paths.get(path)));

        List<String> number = new ArrayList<>();
        number.add("123");
        number.add("456");
        // TODO: 2017/1/8 no such method
        //Files.write(Paths.get("D:\\git\\accumulation\\src\\main\\resources\\io\\nio\\nio2\\number.txt"),null, Charset.defaultCharset());

        Paths.get(path).forEach(p -> System.out.println(p));

        Files.lines(Paths.get(path), Charset.forName("UTF-8")).forEach(line -> System.out.println(line));

        FileStore fileStore = Files.getFileStore(Paths.get("C:"));
        System.out.println(fileStore.getTotalSpace());
        System.out.println(fileStore.getUsableSpace());
    }
}
