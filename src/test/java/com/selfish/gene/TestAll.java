package com.selfish.gene;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/8.
 */
public class TestAll {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void A() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime parse = LocalDateTime.parse("11 4 07:48:34 2016", DateTimeFormatter.ofPattern("MM d HH:mm:ss yyyy"));
        long epochMilli = parse.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(epochMilli);

        Date date = new Date(epochMilli);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }

    @Test
    public void B() throws Exception {
        String path = "C:\\Users\\Administrator\\Documents\\My Hwdoc Files\\HWPDFOCR80\\IMAGE\\疯狂Java讲义  第3版 PDF电子书下载 带书签目录 完整版";
        File file = new File(path);
        File[] files = file.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith(".TXT"));
        if(files != null){
//            Arrays.stream(files).forEach(f -> f.delete());
            Arrays.stream(files).forEach(f -> f.renameTo(new File(path + File.separator + f.getName().substring(f.getName().indexOf("_") + 1).replace(".PDF", ""))));
        }
    }

    @Test
    public void C() throws Exception {

    }
}
