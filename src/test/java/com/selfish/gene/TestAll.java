package com.selfish.gene;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.selfish.gene.thread.synchronized_.Account;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        Account account1 = new Account("a",123);
        Account account2 = new Account("b",689);
        System.out.println(account1.equals(account2));
    }

    // count测试
    @Test
    public void C() throws Exception {
        String s = "abtsbfacsaadfcd";
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            // 每回都需要把count初始化为1
            int count = 1;
            if(map.containsKey(c)){
                count = map.get(c) + 1;
            }
            map.put(c, count);
        }
        System.out.println(map);

        List<Character> list = new ArrayList<>();
        for (char c: chars) {
            list.add(c);
        }
        Multiset<Character> multiset = HashMultiset.create();
        multiset.addAll(list);
        multiset.elementSet().stream().forEach(c ->  System.out.println(c + " = " + multiset.count(c)));
    }
}
