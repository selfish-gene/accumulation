package com.selfish.gene.classloader.customize;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/12.
 */
public class CompileClassLoader extends ClassLoader{


    private byte[] getBytes(String filename) throws IOException{
        File file = new File(filename);
        long len = file.length();
        byte[] bytes = new byte[(int)len];
        try (FileInputStream in = new FileInputStream(file)) {
            int r = in.read(bytes);
            if(r != len)
                throw new IOException("Unable to read the entire contents:\t" + r + "!=" + len);
            return bytes;
        }
    }

    private boolean compile(String javaFile) throws IOException{
        System.out.println("CompileClassLoader:Compiling " + javaFile + "...");
        Process p = Runtime.getRuntime().exec("javac " + javaFile);

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        int ret = p.exitValue();
        return ret == 0;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException{
        Class clazz = null;
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        if(javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())){
            try {
                if (!compile(javaFilename) || !classFile.exists()){
                    throw new ClassNotFoundException("ClassNotFoundException:" + javaFilename);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(classFile.exists()){
            try {
                byte[] raw = getBytes(classFilename);
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws Exception {
        // TODO: 2017/1/13 Can not be compiled 
        if(args.length < 2){
            System.out.println("Missing target class, run java source file in the following format:");
            System.out.println("java CompileClassLoader ClassName");
        }
        String progClass = args[0];
        String[] progArgs = new String[args.length-1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);
        Class<?> clazz = new CompileClassLoader().loadClass(progClass);

        System.out.println((new String[0]).getClass());
        Method main = clazz.getMethod("main", (new String[0]).getClass());
        Object argsArray[] = {progArgs};
        main.invoke(null, argsArray);
    }
}
