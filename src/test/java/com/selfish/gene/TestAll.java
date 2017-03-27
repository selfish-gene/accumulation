package com.selfish.gene;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
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
        String a = "D:\\down\\a";
        File aFile = new File(a);
//        String b = "D:\\down\\b\\e.txt";
        String b = "D:\\down\\b";
        File bFile = new File(b);
        String c = "D:\\logs\\file.log";
//        String c = "D:\\logs";
        File cFile = new File(c);
        String zip = "D:\\down\\a\\a.zip";

        List<File> srcFiles = new ArrayList<>();
        srcFiles.add(aFile);
        srcFiles.add(bFile);
        srcFiles.add(cFile);

        ZipOutputStream  zos;
//        BufferedInputStream bis = null;
        zos = new ZipOutputStream(new FileOutputStream(zip));

        for (File file : srcFiles) {
            if (file.isDirectory()){
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f: files) {
                        String entryName = "a" + File.separator + f.getName();
                        write(zos, f, entryName);
                    }
                }
            }
            else
            {
                String entryName = "ca/" + File.separator + file.getName();
                write(zos, file, entryName);
            }
        }
        IOUtils.closeQuietly(zos);
    }

    private void write(ZipOutputStream zos, File f, String entryName) throws IOException {
        BufferedInputStream bis;
        bis = new BufferedInputStream(new FileInputStream(f));
        zos.putNextEntry(new ZipEntry(entryName));
        byte[] bytes = new byte[1024];
        while(bis.read() != -1){
            zos.write(bytes);
        }
        IOUtils.closeQuietly(bis);
    }

    @Test
    public void B() throws Exception {
        File file = new File("D:\\down\\b\\e.txt");
        assertThat(file.getName(), is("e.txt"));
        assertThat(file.getParentFile().getName(), is("b"));
    }

    @Test
    public void C() throws Exception {

    }
}
