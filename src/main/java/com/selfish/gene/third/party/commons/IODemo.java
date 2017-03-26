package com.selfish.gene.third.party.commons;

/**
 * Created by Administrator on 2017/3/24.
 */
import org.apache.commons.io.*;
import org.apache.commons.io.comparator.CompositeFileComparator;
import org.apache.commons.io.comparator.DirectoryFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.PathFileComparator;
import org.apache.commons.io.filefilter.CanWriteFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class IODemo {

    /**
    * Get the log object
    */
     private static final Logger LOGGER = LogManager.getLogger();

     private static final String LOG4J2 = "D:\\git\\accumulation\\src\\main\\resources\\log4j2.xml";

     private static final String UTF_8 = "UTF-8";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIOUtils() throws Exception {
        InputStream in = new URL("http://commons.apache.org").openStream();
        InputStreamReader inR = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inR);
//        String line;
//        while((line = br.readLine()) != null){
//            System.out.println(line);
//        }
//        in.close();
//        System.out.println(IOUtils.toString(inR));

        InputStream inputStream = new BufferedInputStream(new FileInputStream("D:\\git\\typora\\git\\git.md"));
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("D:\\git\\z_test\\a.txt"));
        int copy = IOUtils.copy(inputStream, outputStream);
        System.out.println(copy);

        IOUtils.closeQuietly(in, inputStream, outputStream);
    }

    @Test
    public void testFileUtils() throws Exception {
        File file = new File("D:\\git\\accumulation\\src\\main\\resources\\log4j2.xml");
        List<String> contents = FileUtils.readLines(file, "UTF-8");
        contents.stream().forEach(c -> System.out.println(c));

        File f = new File("D:\\git\\accumulation\\src\\main\\java\\com\\selfish\\gene\\third\\party\\commons\\io\\validator");
        if (!f.exists()){
            LOGGER.info("File does not exist:" + f.getName());
        } else {
            FileUtils.forceDelete(f);
        }
        FileUtils.copyDirectory(new File("D:\\git\\typora\\git"), new File("D:\\git\\z_test"));
        FileUtils.copyFile(new File("D:\\git\\typora\\git\\git.md"), new File("D:\\git\\z_test\\t.txt"));

    }

    @Test
    public void testFilenameUtils() throws Exception {
        System.out.println(FilenameUtils.separatorsToWindows(LOG4J2));
        assertThat(FilenameUtils.getBaseName(LOG4J2), is("log4j2"));
        assertThat(FilenameUtils.getExtension(LOG4J2), is("xml"));
        System.out.println(FilenameUtils.getFullPath(LOG4J2));
        System.out.println(FilenameUtils.getPrefix(LOG4J2));
    }

    @Test
    public void testFileSystemUtils() throws Exception{
        // 默认文件所在的盘，当前为D盘
        long l = FileSystemUtils.freeSpaceKb();
        System.out.println("可用空间：" + l/(1024 * 1024) + "GB");
        long c = FileSystemUtils.freeSpaceKb("C:/");
        System.out.println("C盘可用空间：" + c/(1024 * 1024) + "GB");
        long d = FileSystemUtils.freeSpaceKb("D:/");
        System.out.println("D盘可用空间：" + d/(1024 * 1024) + "GB");
        long e = FileSystemUtils.freeSpaceKb("E:/");
        System.out.println("E盘可用空间：" + e/(1024 * 1024) + "GB");
    }

    @Test
    public void testLineIterator() throws Exception{
        File file = new File(LOG4J2);
        LineIterator lineIterator = FileUtils.lineIterator(file, UTF_8);
        String line;
        while(lineIterator.hasNext()){
            line = lineIterator.nextLine();
            System.out.println(line);
        }
        LineIterator.closeQuietly(lineIterator);
    }

    @Test
    public void testIOFileFilter() throws Exception{
        // TODO 如何递归去搜索所有的目录
        File file = new File("D:\\git\\accumulation\\src\\main\\java\\com\\selfish\\gene");
//        String[] lists = file.list(DirectoryFileFilter.DIRECTORY);
        String[] lists = file.list(CanWriteFileFilter.CAN_WRITE);
        Arrays.stream(lists).forEach(list -> System.out.println(list));
    }

    @Test
    public void testFileComparators() throws Exception{
        File file = new File("D:\\git\\accumulation\\src\\main\\java\\com\\selfish\\gene");
        File[] list = file.listFiles();
        System.out.println(list[1]);
        System.out.println(list[2]);
        int name = NameFileComparator.NAME_COMPARATOR.compare(list[1], list[2]);
        int path = PathFileComparator.PATH_COMPARATOR.compare(list[1], list[2]);
        System.out.println(name);
        System.out.println(path);

        CompositeFileComparator cfc = new CompositeFileComparator(DirectoryFileComparator.DIRECTORY_COMPARATOR);
        cfc.sort(list);
        System.out.println("*****after sort*****");
        Arrays.stream(list).forEach(l -> System.out.println(l));
    }

}
