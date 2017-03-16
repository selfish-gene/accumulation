package com.selfish.gene.reflect;

import com.selfish.gene.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2017/3/15.
 */
public class ExtendedObjectPoolFactory {

    /*
     * Get the log object
     */
     private static final Logger LOGGER = LogManager.getLogger();
    // 定义一个对象池,前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    private Properties props = new Properties();
    // 从指定属性文件中初始化Properties对象
    public void init(String fileName){
        try (FileInputStream fis = new FileInputStream(ExtendedObjectPoolFactory.class.getResource("/").getPath() + File.separator + fileName)) {
            props.load(fis);
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found");
        } catch (IOException e) {
            LOGGER.error("IO error");
        }
    }
    // 定义一个创建对象的方法，
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }
    // 该方法根据指定文件来初始化对象池，
    // 它会根据配置文件来创建对象
    public void initPool() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String name : props.stringPropertyNames())
        {
            // 每取出一对key-value对，如果key中不包含百分号（%）,每取出一对key-value对，就根据value创建一个对象
            // 调用createObject()创建对象，并将对象添加到对象池中
            if (!name.contains("%")) {
                objectPool.put(name , createObject(props.getProperty(name)));
            }
        }
    }
    // 该方法将会根据属性文件来调用指定对象的setter方法
    public void initProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for(String name : props.stringPropertyNames()){
            if (name.contains("%")){
                String[] strs = name.split("%");
                // 取出调用setter方法的对象
                Object target = getObject(strs[0]);
                // 获取setter方法名:set + "首字母大写" + 剩下部分
                String methodName = "set" + strs[1].substring(0,1).toUpperCase() + strs[1].substring(1);
                Class<?> targetClass = target.getClass();
                Method method = targetClass.getMethod(methodName, String.class);
                // 通过Method的invoke方法执行setter方法，
                // 将config.getProperty(name)的值作为调用setter的方法的参数
                method.invoke(target, props.getProperty(name));
            }
        }
    }

    // 从objectPool中取出指定name对应的对象
    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) throws Exception{
        User user = (User) Class.forName("com.selfish.gene.modles.User").newInstance();
        Method method = user.getClass().getMethod("setName", String.class);
        method.invoke(user, "selfish_gene");
        System.out.println(user);

        ExtendedObjectPoolFactory epf  = new ExtendedObjectPoolFactory();
        epf.init("obj.properties");
        epf.initPool();
        epf.initProperty();
        System.out.println(epf.getObject("user"));

    }

}
