package com.selfish.jene.io.nio2.file_attribute;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */
public class AttributeViewTest {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\git\\accumulation\\src\\main\\java\\com\\my\\io\\nio2\\file_attribute\\AttributeViewTest.java";
        Path testPath = Paths.get(filePath);

        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        BasicFileAttributes basicAttrs = basicView.readAttributes();
        System.out.println("Created time:\t" + new Date(basicAttrs.creationTime().toMillis()));
        System.out.println("Last access time:\t" + new Date(basicAttrs.lastAccessTime().toMillis()));
        System.out.println("Last Modified time:\t" + new Date(basicAttrs.lastModifiedTime().toMillis()));
        System.out.println("File size:\t" + basicAttrs.size());

        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        System.out.println(ownerView.getOwner());
        UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
        ownerView.setOwner(user);
        System.out.println(ownerView.getOwner());

        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        for (String name: attrNames) {
            ByteBuffer buffer = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buffer);
            buffer.flip();
            String value = Charset.defaultCharset().decode(buffer).toString();
            System.out.println(name + " ---> " + value);
        }
        userView.write("writer", Charset.defaultCharset().encode("anlei"));

        DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
        dosView.setHidden(true);
        dosView.setReadOnly(false);
    }
}
