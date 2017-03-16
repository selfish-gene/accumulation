package com.selfish.gene.reflect;

import com.sun.rowset.internal.WebRowSetXmlReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/15.
 */
public class ObjectPoolFactory {
    // 定义一个对象池,前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    // 定义一个创建对象的方法，
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }
    // 该方法根据指定文件来初始化对象池，
    // 它会根据配置文件来创建对象
    public void initPool(String fileName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File(ObjectPoolFactory.class.getResource("/").getPath() + File.separator + fileName);
        try (FileInputStream fis = new FileInputStream(file)){
            Properties props = new Properties();
            props.load(fis);
//            props.stringPropertyNames().forEach(name -> objectPool.put(name, creatObject(props.getProperty(name))));

            for (String name : props.stringPropertyNames())
            {
                // 每取出一对key-value对，就根据value创建一个对象
                // 调用createObject()创建对象，并将对象添加到对象池中
                objectPool.put(name , createObject(props.getProperty(name)));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found class");
        } catch (IOException e) {
            System.out.println("读取" + fileName + "异常");
        }
    }
    // 从objectPool中取出指定name对应的对象
    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        ObjectPoolFactory pf  = new ObjectPoolFactory();
        pf.initPool("obj.properties");
        System.out.println(pf.getObject("date"));
        System.out.println(pf.getObject("user"));

    }

}
